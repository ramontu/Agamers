#!/usr/bin/python
# -*- coding: utf-8 -*-

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError

import messages
from db.models import Platforms
from hooks import requires_platform_id, mylogger
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaNewPlatform


class ResourceNewPlatform(DAMCoreResource):
    @jsonschema.validate(SchemaNewPlatform)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceNewPlatform, self).on_post(req, resp, *args, **kwargs)
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


@falcon.before(requires_platform_id)
class ResourceDeletePlatform(DAMCoreResource):
    def on_delete(self, req, resp, *args, **kwargs):
        super(ResourceDeletePlatform, self).on_delete(self, req, resp, *args, **kwargs)
        current_platform = req.context["platform"]

        try:
            self.db_session.delete(current_platform)
            self.db_session.commit()

            resp.status = falcon.HTTP_200
        except Exception as e:
            mylogger.critical("{}:{}".format(messages.error_removing_platform, e))
            raise falcon.HTTPInternalServerError()


class ResourceGetPlatforms(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetPlatforms, self).on_get(req, resp, *args, **kwargs)
        platforms = self.db_session.query(Platforms)
        response = []

        if 'id' in req.media:
            platforms = platforms.filter(Platforms.id == req.media["id"]).one()
            response = platforms.json_model
        elif 'name' in req.media:
            platforms = platforms.filter(Platforms.name == req.media["name"]).one()
            response = platforms.json_model
        elif 'manufacturer' in req.media:
            platforms = platforms.filter(Platforms.manufacturer == req.media["manufacturer"])
            for i in platforms:
                response.append(i.json_model)
        else:
            for i in platforms:
                response.append(i.json_model)
        resp.media = response
        resp.status = falcon.HTTP_200
