#!/usr/bin/python
# -*- coding: utf-8 -*-

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound

import messages
from db.models import Jocs, Categories, Platforms, Xats, User
from hooks import requires_game_id
from resources import utils
from resources.base_resources import DAMCoreResource, mylogger
from resources.schemas import SchemaNewGame, SchemaUpdateGame


class ResourceAllUserChats(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceAllUserChats, self).on_get(req, resp, *args, **kwargs)

        print(kwargs)
        if "id" in kwargs:
            try:
                chats = self.db_session.query()


            except NoResultFound:
                raise falcon.HTTPBadRequest(description=messages.user_not_found)


class ResourceAddUserToChat(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceAddUserToChat, self).on_post(req, resp, *args, **kwargs)

        print(kwargs)

        # TODO buscar si aquest xat existeix, si no existei crear-lo i si no només afegir-lo al usuari que li passem

        if ("user_id" and "chat_id") in kwargs:

            # TODO obtenir l'usuari al que es vol afegir el xat
            # TODO afegir a user.xats el xat que acabem de crear

            user = self.db_session.query(User).filter(User.id == kwargs["user_id"]).one_or_none()
            if user is not None:
                xat = self.db_session.query(Xats).filter(Xats.firebase_chat_id == kwargs["chat_id"]).one_or_none()
                if xat is None:
                    # Si el xat no existeix el creem
                    # TODO fer-ho amb un try pk sino podrà fallar
                    xat = Xats()
                    xat.firebase_chat_id = kwargs["chat_id"]
                    self.db_session.add(xat)
                    self.db_session.commit()
                # Afegim el xat al personatge
                # TODO fer try exept
                user.xats.append(xat)
                self.db_session.add(user)
                self.db_session.commit()

            else:
                raise falcon.HTTPBadRequest(description=messages.user_not_found)
        else:
            raise falcon.HTTPBadRequest(description="S'ha de passar un chat_id i un user_id ")

        resp.status = falcon.HTTP_200
