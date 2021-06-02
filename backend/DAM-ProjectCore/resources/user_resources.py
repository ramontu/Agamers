#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound

from resources import utils
import messages
from db.models import User, GenereEnum, AccountTypeEnum
from hooks import requires_auth
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaRegisterUser


mylogger = logging.getLogger(__name__)

class ResourceMatchingCalculateScore(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceMatchingCalculateScore, self).on_post(req, resp, *args, **kwargs)

        # Get the user from the token
        current_user = req.context["auth_user"]

        # Get all users in db different from the one that request
        users = self.db_session.query(User).filter(current_user.id != User.id).all()

        for user in users:

            # Check if exists a match
            match = self.db_session.query(Matching_data).filter(and_(
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
                    user1 = current_user.id,
                    user2 = user.id,
                    common_games = games_common,
                    age_diff = dif_age,
                    score = games_common / dif_age
                )

                self.db_session.add(aux)
                self.db_session.commit()

            else:
                if match.isAMatch == False:
                    match.common_games = games_common,
                    match.age_diff = dif_age,
                    match.score = games_common / dif_age

                self.db_session.commit()


class ResourceGetUserProfile(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetUserProfile, self).on_get(req, resp, *args, **kwargs)
    ResourceMatchingCalculateScore()
        if "username" in kwargs:
            try:
                aux_user = self.db_session.query(User).filter(User.username == kwargs["username"]).one()

                resp.media = aux_user.public_profile
                resp.status = falcon.HTTP_200
            except NoResultFound:
                raise falcon.HTTPBadRequest(description=messages.user_not_found)


class ResourceRegisterUser(DAMCoreResource):
    @jsonschema.validate(SchemaRegisterUser)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceRegisterUser, self).on_post(req, resp, *args, **kwargs)

        aux_user = User()

        try:
            aux_user.username = req.media["username"]
            aux_user.password = req.media["password"]
            aux_user.email = req.media["email"]
            aux_user.birthday = req.media["birthday"]

            self.db_session.add(aux_user)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.user_exists)

        except KeyError:
            raise falcon.HTTPBadRequest(description="user_resources(): Parametres incorrectes")

        resp.status = falcon.HTTP_200


# TODO comprovar que funciona
# Sera substituida per una altra fuciona a common resources
@falcon.before(requires_auth)
class DownloadUserImage(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(DownloadUserImage, self).on_get(req, resp, *args, **kwargs)
        # Get the user from the token
        current_user = req.context["auth_user"]

        # Run the common part for reading
        file = utils.get_static_media_file(current_user.photo_path)
        resp.media = file
        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class DownloadMenuInfo(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(DownloadMenuInfo, self).on_get(req, resp, *args, **kwargs)
        current_user = req.context["auth_user"]

        file = utils.get_static_media_file(current_user.photo_path)
        resp.media = [file, current_user.username]
        resp.status = falcon.HTTP_200
