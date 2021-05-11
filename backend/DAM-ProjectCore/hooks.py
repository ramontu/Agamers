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
    game_id = req.get_header("GameId")
    if game_id is not None:
        current_game_id = resource.db_session.query(Jocs).filter(Jocs.id == game_id).one_or_none()
        if current_game_id is not None:
            req.context["game_id"] = current_game_id
        else:
            raise falcon.HTTPUnauthorized(description=messages.game_id_invalid)
    else:
        raise falcon.HTTPUnauthorized(description=messages.game_id_required)

