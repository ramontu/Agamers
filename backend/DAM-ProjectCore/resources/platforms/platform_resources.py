#!/usr/bin/python
# -*- coding: utf-8 -*-

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError

import messages
from db.models import Platforms
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaNewPlatform


class ResourceNewPlatform(DAMCoreResource):
    @jsonschema.validate(SchemaNewPlatform)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceNewPlatform, self).on_post(self, req, resp, *args, **kwargs)
        aux_platform = Platforms()

        try:
            for i in req.media:
                valor = req.media[i]
                setattr(aux_platform, i, valor)

            self.db_session.add(aux_platform)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.platform_exists)
        except KeyError:
            raise falcon.HTTPBadRequest(description="platform_resources(): Parametres incorrectes")

        resp.status = falcon.HTTP_200

