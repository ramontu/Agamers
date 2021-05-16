#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging.config

import falcon

import messages
import middlewares
from falcon_multipart.middleware import MultipartMiddleware
from resources import account_resources, common_resources, user_resources, games_resources
from resources.categories import categories_resources
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

application.add_route("/users/register", user_resources.ResourceRegisterUser())
application.add_route("/users/show/{username}", user_resources.ResourceGetUserProfile())
application.add_route("/users/getuserimage", user_resources.DownloadUserImage())  # TODO comprovar

# Jocs
application.add_route("/jocs/create_jocs", games_resources.ResourceNewGame())
application.add_route("/jocs/update_jocs/{id:int}", games_resources.ResourceUpdateGame())  # TODO provar
application.add_route("/jocs/delete_jocs/{id:int}", games_resources.ResourceDeleteGame())  # TODO provar
application.add_route("/jocs", games_resources.ResourceGetGames())  # OK
application.add_route("/jocs/update_profile_image", games_resources.ResourceJocsUpdateImage())  # TODO provar

# Plataforma
application.add_route("/platform/create", platform_resources.ResourceNewPlatform())  # TODO provar
application.add_route("/platform/delete/{id:int}", platform_resources.ResourceDeletePlatform())  # TODO provar
application.add_route("/platforms", platform_resources.ResourceGetPlatforms())  # TODO provar

# Categories
application.add_route("/category/create", categories_resources.ResourceNewCategory())  # TODO provar
application.add_route("/category/delete/{id:int}", categories_resources.ResourceDeleteCategory())  # TODO provar
application.add_route("/categories", categories_resources.ResourceGetCategories())  # OK

# General
application.add_route("/image/download", common_resources.ResourceDownloadImage())  # TODO provar

'''
application.add_route("/events", event_resources.ResourceGetEvents())
application.add_route("/events/show/{id:int}", event_resources.ResourceGetEvent())
'''
application.add_sink(handle_404, "")
