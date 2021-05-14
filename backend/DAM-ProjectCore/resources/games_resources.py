import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError

import messages
from db.models import Jocs
from hooks import requires_game_id
from resources.base_resources import DAMCoreResource, mylogger
from resources.schemas import SchemaNewGame, SchemaUpdateGame


class ResourceNewGame(DAMCoreResource):
    @jsonschema.validate(SchemaNewGame)
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceNewGame, self).on_post(req, resp, *args, **kwargs)

        aux_game = Jocs()
        try:
            for i in req.media:  # ToDO es possible que falli amb més d'una categoria
                valor = req.media[i]
                setattr(aux_game, i, valor)

            self.db_session.add(aux_game)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.game_exists)

        except KeyError:
            raise falcon.HTTPBadRequest(description="game_resources(): Parametres incorrectes")

        resp.status = falcon.HTTP_200


@falcon.before(requires_game_id)
class ResourceUpdateGame(DAMCoreResource):
    @jsonschema.validate(SchemaUpdateGame)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceUpdateGame, self).on_post(req, resp, *args, **kwargs)
        current_game = req.context["game_id"]
        try:
            for i in req.media:  # ToDO es possible que falli amb més d'una categoria
                valor = req.media[i]
                setattr(current_game, i, valor)

            self.db_session.add(current_game)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.game_exists)

        except KeyError:
            raise falcon.HTTPBadRequest(description="game_resources(): Parametres incorrectes")

        resp.status = falcon.HTTP_200


@falcon.before(requires_game_id)
class ResourceDeleteGame(object):
    def on_delete(self, req, resp, *args, **kwargs):
        super(ResourceDeleteGame, self).on_delete(req, resp, *args, **kwargs)
        current_game = req.context["game_id"]

        try:
            self.db_session.delete(current_game)
            self.db_session.commit()

            resp.status = falcon.HTTP_200
        except Exception as e:
            mylogger.critical("{}:{}".format(messages.error_removing_game, e))
            raise falcon.HTTPInternalServerError()
