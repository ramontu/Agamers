#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

import falcon

import messages
from db.models import UserToken, Jocs

mylogger = logging.getLogger(__name__)


# noinspection PyUnusedLocal
def requires_auth(req, resp, resource, params):
    auth_token = req.get_header("Authorization")
    if auth_token is not None:
        current_token = resource.db_session.query(UserToken).filter(UserToken.token == auth_token).one_or_none()
        if current_token is not None:
            req.context["auth_user_token"] = current_token
            req.context["auth_user"] = current_token.user
        else:
            raise falcon.HTTPUnauthorized(description=messages.token_invalid)
    else:
        raise falcon.HTTPUnauthorized(description=messages.token_required)


def requires_game_id(req, resp, resource, params):
    print(params)
    print("h")
    print(req)

    if "id" in params:
        game = resource.db_session.query(Jocs).filter(Jocs.id == params["id"]).one()
        if game is not None:
            req.context["game"] = game
        else:
            raise falcon.HTTPUnauthorized(description=messages.game_not_found)
    else:
        raise falcon.HTTPUnauthorized(description=messages.game_id_required)
