#!/usr/bin/python
# -*- coding: utf-8 -*-
import logging

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound
from sqlalchemy import or_, and_

from resources import utils
import messages
from db.models import User, Matching_data
from hooks import requires_auth
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaRegisterUser


from datetime import datetime
from dateutil.relativedelta import relativedelta

mylogger = logging.getLogger(__name__)

#user = [[8, 12], [6, 6], [9, 3], [10, 6], [14, 5]] #jocs en comú, diferencia d'edat
#result=[0]*len(user)
def recalculate_score(current_user):
    import db
    db_session = db.create_db_session()
    # Get all users in db different from the one that request
    users = db_session.query(User).filter(current_user.id != User.id).all()

    for user in users:

        # Check if exists a match
        match = db_session.query(Matching_data).filter(and_(
            or_(
                Matching_data.user1 == current_user.id,
                Matching_data.user2 == current_user.id
            ),
            or_(
                Matching_data.user1 == user.id,
                Matching_data.user2 == user.id
            ))).one_or_none()

        # Calculte diff age
        if (current_user.birthday > user.birthday):
            dif_age = relativedelta(current_user.birthday, user.birthday).years
        else:
            dif_age = relativedelta(user.birthday, current_user.birthday).years

        if (dif_age == 0):
            dif_age = 1

        # Calculate common_games
        setintersection = list(set(current_user.games) & set(user.games))
        games_common = (len(setintersection))

        if match is None:
            aux = Matching_data(
                user1=current_user.id,
                user2=user.id,
                common_games=games_common,
                age_diff=dif_age,
                score=games_common / dif_age
            )

            db_session.add(aux)
            db_session.commit()

        else:
            if match.isAMatch == False:
                match.common_games = games_common,
                match.age_diff = dif_age,
                match.score = games_common / dif_age

            db_session.commit()

    db_session.close()

@falcon.before(requires_auth)
class Resource_Get_Matchs(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(Resource_Get_Matchs, self).on_get(req, resp, *args, **kwargs)

        # Get the user from the token
        current_user = req.context["auth_user"]

        cursor = self.db_session.query(Matching_data).filter(and_(
                                                                 or_(
                                                                Matching_data.user1 == current_user.id,
                                                                Matching_data.user2 == current_user.id
                                                                 ), Matching_data.isAMatch==False)).order_by(
            Matching_data.score.desc()
        )

        matchs = list()

        # all() retorna tot aixi marquem que retorni 3 maxim -> limit(3)
        for c in cursor.limit(3):
            if c.user1 != current_user.id:
                matchs.append(c.user1)
            else:
                matchs.append(c.user2)

        print(matchs)



