#!/usr/bin/python
# -*- coding: utf-8 -*-

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError

import messages
from db.models import Categories
from resources.base_resources import DAMCoreResource

# TODO no funciona
from resources.schemas import SchemaNewCategory


class ResourceNewCategory(DAMCoreResource):
    @jsonschema.validate(SchemaNewCategory)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceNewCategory, self).on_post(req, resp, *args, **kwargs)
        aux_category = Categories()
        try:
            aux_category.name = kwargs["name"]
            self.db_session.add(aux_category)
            try:
                self.db_session.commit()
                resp.status = falcon.HTTP_200
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.category_exists)

        except KeyError:
            raise falcon.HTTPBadRequest(description="categories_resources(): Parametres incorrectes")


class ResourceDeleteCategory(DAMCoreResource):

    def on_delete(self, req, resp, *args, **kwargs):
        super(ResourceDeleteCategory, self).on_delete(req, resp, *args, **kwargs)

        selected_category_string = req.media["name"]
        selected_category = self.db_session.query(Categories).filter(
            Categories.name == selected_category_string).one_or_none()

        if selected_category is not None:
            try:
                self.db_session.delete(selected_category)
                self.db_session.commit()

                resp.status = falcon.HTTP_200
            except Exception as e:
                # mylogger.critical("{}:{}".format(messages.error_removing_category, e))
                raise falcon.HTTPInternalServerError()
        else:
            raise falcon.HTTPUnauthorized(description=messages.category_not_found)

# FUNCIONA
class ResourceGetCategories(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetCategories, self).on_get(req, resp, *args, **kwargs)
        # Pot buscar per nom o per id
        categories = self.db_session.query(Categories)
        resultat = []
        if 'id' in req.media:
            categories = categories.filter(Categories.id == req.media["id"]).one()
            resultat = categories.json_model
        elif 'name' in req.media:
            categories = categories.filter(Categories.name == req.media["name"]).one()
            resultat = categories.json_model
        else:
            for i in categories:
                resultat.append(i.json_model)

        resp.media = resultat
        resp.status = falcon.HTTP_200
