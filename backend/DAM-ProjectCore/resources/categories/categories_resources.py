import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError

import messages
from db.models import Categories, mylogger
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaNewCategory


class ResourceNewCategory(DAMCoreResource):
    @jsonschema.validate(SchemaNewCategory)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceNewCategory, self).on_post(self, req, resp, *args, **kwargs)
        aux_category = Categories()

        try:
            for i in req.media:
                valor = req.media[i]
                setattr(aux_category, i, valor)

            self.db_session.add(aux_category)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.category_exists)
        except KeyError:
            raise falcon.HTTPBadRequest(description="categories_resources(): Parametres incorrectes")

        resp.status = falcon.HTTP_200


class ResourceDeleteCategory(DAMCoreResource):
    @jsonschema.validate(SchemaNewCategory)
    def on_delete(self, req, resp, *args, **kwargs):
        super(ResourceDeleteCategory, self).on_delete(self, req, resp, *args, **kwargs)

        selected_category_string = req.media["name"]
        selected_category = self.db_session.query(Categories).filter(
            Categories.name == selected_category_string).one_or_none()

        if selected_category is not None:
            try:
                self.db_session.delete(selected_category)
                self.db_session.commit()

                resp.status = falcon.HTTP_200
            except Exception as e:
                mylogger.critical("{}:{}".format(messages.error_removing_category, e))
                raise falcon.HTTPInternalServerError()
        else:
            raise falcon.HTTPUnauthorized(description=messages.category_not_found)
