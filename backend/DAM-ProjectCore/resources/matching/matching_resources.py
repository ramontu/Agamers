#!/usr/bin/python
# -*- coding: utf-8 -*-
import logging

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound

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

@falcon.before(requires_auth)
class ResourceMatchingTest(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceMatchingTest, self).on_post(req, resp, *args, **kwargs)

        # Get the user from the token
        current_user = req.context["auth_user"]

        # Get all users in db different from the one that request
        users = self.db_session.query(User).filter(current_user.id != User.id).all()

        for user in users:
            #jocs_en_comu = user[i][0]
            #dif_edat = user[i][1]

            # Calculte diff age
            if (current_user.birthday > user.birthday):
                dif_age = relativedelta(current_user.birthday, user.birthday).years
            else:
                dif_age = relativedelta(user.birthday, current_user.birthday).years

            if (dif_age == 0):
                dif_age = 1

            print(dif_age)

            # Calculate common_games
            setintersection = list(set(current_user.games) & set(user.games))
            games_common = (len(setintersection))

            games_common = games_common**2

            print(games_common)

            aux = Matching_data(
                user1 = current_user.id,
                user2 = user.id,
                common_games = games_common,
                age_diff = dif_age,
                score = games_common / dif_age
            )

            print(aux.json_model)

            self.db_session.add(aux)
            self.db_session.commit()



class Resource_Recalculate_Matching_Score(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(Resource_Recalculate_Matching_Score, self).on_post(req, resp, *args, **kwargs)

        if "id" in kwargs:
            user_who_calculates = self.db_session.query(User).filter(User.id == kwargs["id"]).one()
            users = self.db_session.query(User).all()

            user_who_game_list = user_who_calculates.games_played

            llista_ids_user = []
            for i in user_who_game_list:
                llista_ids_user.append(i.id)

            for i in users:
                if i.id is not user_who_calculates.id:
                    llista_games_other = i.games_played
                    llista_ids_other = []
                    for o in llista_games_other:
                        llista_ids_other.append(o.id)
                    puntuacio = 0

            print(llista_ids)



            '''
            result = [0] * len(user)
            for i in users:
                jocs_en_comu = user[i][0]
                dif_edat = user[i][1]
                if (dif_edat == 0):
                    dif_edat = 1
                n = jocs_encomu
                for in range(2):
                    jocs_en_comu = jocs_en_comu * n
                result[i] = jocs_en_comu / dif_edat
            '''