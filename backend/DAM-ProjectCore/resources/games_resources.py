import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound

import messages
from db.models import Jocs, Categories, Platforms
from hooks import requires_game_id
from resources.base_resources import DAMCoreResource, mylogger
from resources.schemas import SchemaNewGame, SchemaUpdateGame


class ResourceNewGame(DAMCoreResource):
    @jsonschema.validate(SchemaNewGame)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceNewGame, self).on_post(req, resp, *args, **kwargs)

        aux_game = Jocs()
        try:
            for i in req.media:  # ToDO es possible que falli amb més d'una categoria
                print("passo per " + i)
                valor = req.media[i]
                if i == "categories":  # TODO fer el mateix amb les plataformes
                    aux_game.categories = []
                    for k in valor:
                        aux = self.db_session.query(Categories).filter(Categories.id == k).one_or_none()
                        if aux is not None:
                            print("Categoria trobada" + aux.name)
                            aux_game.categories.append(aux)

                if i == "platforms":
                    aux_game.platforms = []
                    for k in valor:
                        aux = self.db_session.query(Platforms).filter(Platforms.id == k).one_or_none()
                        if aux is not None:
                            print("Plataforma trobada" + aux.name)
                            aux_game.platforms.append(aux)

                else:
                    setattr(aux_game, i, valor)

            self.db_session.add(aux_game)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.game_exists)
        except KeyError:
            raise falcon.HTTPBadRequest(description="game_resources(): Parametres incorrectes")
        resp.status = falcon.HTTP_200


# TODO falta provar
@falcon.before(requires_game_id)
class ResourceUpdateGame(DAMCoreResource):
    @jsonschema.validate(SchemaUpdateGame)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceUpdateGame, self).on_post(req, resp, *args, **kwargs)
        current_game = req.context["game"]
        try:
            for i in req.media:
                valor = req.media[i]
                if i == "categories":
                    current_game.categories = []
                    for k in valor:
                        aux = self.db_session.query(Categories).filter(Categories.name == k).one_or_none()
                        if aux is not None:
                            current_game.categories.append(aux)

                if i == "platforms":
                    current_game.platforms = []
                    for k in valor:
                        aux = self.db_session.query(Platforms).filter(Platforms.name == k).one_or_none()
                        if aux is not None:
                            current_game.platforms.append(aux)
                else:
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
        current_game = req.context["game"]

        try:
            self.db_session.delete(current_game)
            self.db_session.commit()

            resp.status = falcon.HTTP_200
        except Exception as e:
            mylogger.critical("{}:{}".format(messages.error_removing_game, e))
            raise falcon.HTTPInternalServerError()


@falcon.before(requires_game_id)
class ResourceGetGame(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetGame, self).on_get(req, resp, *args, **kwargs)
        current_game = req.context["game"]

        if current_game is not None:
            try:
                resp.media = current_game.json_model
                resp.status = falcon.HTTP_200
            except NoResultFound:
                raise falcon.HTTPBadRequest(description=messages.game_not_found)


# FUNCIONA
class ResourceGetGames(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetGames, self).on_get(req, resp, *args, **kwargs)

        query = self.db_session.query(Jocs)

        plat_buscar = req.get_param("platforms")
        cat_buscar = req.get_param("categories")
        if (plat_buscar is not None) or (cat_buscar is not None):
            print("Entro")
            if plat_buscar is not None:
                query = query.join(Jocs.Platforms_game, Platforms).filter(Platforms.id.in_(plat_buscar))
            if cat_buscar is not None:
                query = query.join(Jocs.Categories_game, Categories).filter(Categories.id.in_(cat_buscar))
        print(query)
        results = query.all()
        games = []
        if results is not None:
            for i in results:
                games.append(i.json_model)
        resp.media = games
        resp.status = falcon.HTTP_200
