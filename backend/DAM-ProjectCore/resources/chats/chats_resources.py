#!/usr/bin/python
# -*- coding: utf-8 -*-

import falcon
from sqlalchemy.orm.exc import NoResultFound

import messages
from db.models import Xats, User
from resources.base_resources import DAMCoreResource


class ResourceAllUserChats(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceAllUserChats, self).on_get(req, resp, *args, **kwargs)
        if "id" in kwargs:
            try:
                user = self.db_session.query(User).filter(User.id == kwargs["id"]).one_or_none()
                res = []
                for i in user.xats:
                    res.append(i.json_model)
                resp.media = res
            except NoResultFound:
                raise falcon.HTTPBadRequest(description=messages.user_not_found)
            resp.status = falcon.HTTP_200
        else:
            raise falcon.HTTPInvalidParam(description="Falta passar el id")


class ResourceAddUserToChat(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceAddUserToChat, self).on_post(req, resp, *args, **kwargs)
        if ("user_id" and "chat_id") in kwargs:

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
