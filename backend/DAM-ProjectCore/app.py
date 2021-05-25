#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging.config

import falcon

import messages
import middlewares
from falcon_multipart.middleware import MultipartMiddleware
from resources import account_resources, common_resources, user_resources, games_resources
from resources.categories import categories_resources
from resources.chats import chats_resources
from resources.matching import matching_resources
from resources.platforms import platform_resources
from settings import configure_logging

# LOGGING
mylogger = logging.getLogger(__name__)
configure_logging()


# DEFAULT 404
# noinspection PyUnusedLocal
def handle_404(req, resp):
    resp.media = messages.resource_not_found
    resp.status = falcon.HTTP_404


# FALCON
app = application = falcon.API(
    middleware=[
        middlewares.DBSessionManager(),
        middlewares.Falconi18n(),
        MultipartMiddleware()
    ]
)
application.add_route("/", common_resources.ResourceHome())

application.add_route("/account/profile", account_resources.ResourceAccountUserProfile())
application.add_route("/account/profile/update_profile_image", account_resources.ResourceAccountUpdateProfileImage())
application.add_route("/account/create_token", account_resources.ResourceCreateUserToken())
application.add_route("/account/delete_token", account_resources.ResourceDeleteUserToken())
application.add_route("/account/delete_account", account_resources.ResourceAccountDelete())
application.add_route("/account/update_account", account_resources.ResourceAccountUpdate())

# Verificació recuperació
application.add_route("/account/recovery", account_resources.ResourceAccountRecovery())
application.add_route("/account/password_update", account_resources.ResourceAccountPasswordUpdate())

# USER
application.add_route("/users/register", user_resources.ResourceRegisterUser())
application.add_route("/users/show/{username}", user_resources.ResourceGetUserProfile())
application.add_route("/users/getuserimage", user_resources.DownloadUserImage())  # TODO comprovar
application.add_route("/users/send_request/{sender}/{reciver}", user_resources)  # TODO FER
application.add_route("/user/download_menu_items", user_resources.DownloadMenuInfo())  # TODO comprovar

# Jocs
application.add_route("/jocs/create_jocs", games_resources.ResourceNewGame())
application.add_route("/jocs/update_jocs/{id:int}", games_resources.ResourceUpdateGame())  # TODO provar
application.add_route("/jocs/delete_jocs/{id:int}", games_resources.ResourceDeleteGame())  # TODO provar
application.add_route("/jocs/get_game/{id:int}", games_resources.ResourceGetGame())  # OK
application.add_route("/jocs", games_resources.ResourceGetGames())  # OK
application.add_route("/jocs/update_profile_image", games_resources.ResourceJocsUpdateImage())  # TODO provar

# Plataforma
application.add_route("/platform/create", platform_resources.ResourceNewPlatform())  # OK
application.add_route("/platform/delete/{id:int}", platform_resources.ResourceDeletePlatform())  # TODO provar
application.add_route("/platforms", platform_resources.ResourceGetPlatforms())  # OK

# Categories
application.add_route("/category/create", categories_resources.ResourceNewCategory())  # OK
application.add_route('/category/delete/{id:int}', categories_resources.ResourceDeleteCategory())  # TODO provar
application.add_route("/categories", categories_resources.ResourceGetCategories())  # OK

# Xats
application.add_route("/xats/all/{id}", chats_resources.ResourceAllUserChats())  # TODO provar
application.add_route("/xats/add_user_to_chat/{chat_id}/{user_id:int}",
                      chats_resources.ResourceAddUserToChat())  # TODO provar

# General
application.add_route("/image/download", common_resources.ResourceDownloadImage())  # TODO provar

# Matching
application.add_route("/matching/recalculate/{id:int}", matching_resources.Resource_Recalculate_Matching_Score())
'''
application.add_route("/events", event_resources.ResourceGetEvents())
application.add_route("/events/show/{id:int}", e vent_resources.ResourceGetEvent())
'''
application.add_sink(handle_404, "")
