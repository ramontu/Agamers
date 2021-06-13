#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

import falcon
from sqlalchemy.orm import joinedload
from sqlalchemy.orm.exc import NoResultFound

import messages
from resources.base_resources import DAMCoreResource
from resources.utils import get_static_media_file

mylogger = logging.getLogger(__name__)


class ResourceHome(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceHome, self).on_get(req, resp, *args, **kwargs)

        resp.media = messages.welcome_message
        resp.status = falcon.HTTP_200


# TODO: No fa falta
class ResourceDownloadImage(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceDownloadImage, self).on_get(self, req, resp, *args, **kwargs)

        if "path" in kwargs:
            address = kwargs["path"]
            resp.media = get_static_media_file(address)
            if resp.media is None:
                raise falcon.HTTPBadRequest("No s'ha trobar cap imatge amb aquest path")
        else:
            raise falcon.HTTPBadRequest("No s'ha trobat cap cabecera anomenada path")