#!/usr/bin/python
# -*- coding: utf-8 -*-

import binascii
import datetime
import enum
import logging
import os
from _operator import and_
from builtins import getattr
from urllib.parse import urljoin

import falcon
from passlib.hash import pbkdf2_sha256
from sqlalchemy import Column, Date, DateTime, Enum, ForeignKey, Integer, Unicode, \
    UnicodeText, Table, type_coerce, case, Boolean
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.ext.hybrid import hybrid_method, hybrid_property
from sqlalchemy.orm import relationship
from sqlalchemy_i18n import make_translatable

import messages
from db.json_model import JSONModel
import settings

mylogger = logging.getLogger(__name__)

SQLAlchemyBase = declarative_base()
make_translatable(options={"locales": settings.get_accepted_languages()})


def _generate_media_url(class_instance, class_attibute_name, default_image=False):
    class_base_url = urljoin(urljoin(urljoin("http://{}".format(settings.STATIC_HOSTNAME), settings.STATIC_URL),
                                     settings.MEDIA_PREFIX),
                             class_instance.__tablename__ + "/")
    class_attribute = getattr(class_instance, class_attibute_name)
    if class_attribute is not None:
        return urljoin(urljoin(urljoin(urljoin(class_base_url, class_attribute), str(class_instance.id) + "/"),
                               class_attibute_name + "/"), class_attribute)
    else:
        if default_image:
            return urljoin(urljoin(class_base_url, class_attibute_name + "/"), settings.DEFAULT_IMAGE_NAME)
        else:
            return class_attribute


def _generate_media_path(class_instance, class_attibute_name):
    class_path = "/{0}{1}{2}/{3}/{4}/".format(settings.STATIC_URL, settings.MEDIA_PREFIX, class_instance.__tablename__,
                                              str(class_instance.id), class_attibute_name)
    return class_path


class GenereEnum(enum.Enum):
    male = "M"
    female = "F"
    no_binary = "NB"
    not_specified = "N"


# TODO implementar mes endavant: implementar amb els tornejos
'''
class EventTypeEnum(enum.Enum): #TODO modificar per adaptar a tornejos
    hackathon = "H"
    lanparty = "LP"
    livecoding = "LC"
'''


class AccountTypeEnum(enum.Enum):  # TODO provar
    store = "S"
    free = "F"
    premium = "P"


class UserTypeEnum(enum.Enum):  # TODO provar i acabar
    competitive = "Comp"
    casual = "Casu"


class UserBanned(enum.Enum):  # TODO provar
    permanent = "Perma"
    provisional = "Provi"
    no = "No"


# TODO implementar mes endavant: Implementar amb tornejos
'''
class EventStatusEnum(enum.Enum): 
    open = "O"
    closed = "C"
    ongoing = "G"
    undefined = "U"
'''
# TODO: implementar mes endavant: implementar amb tornejos
'''
EventParticipantsAssociation = Table("event_participants_association", SQLAlchemyBase.metadata,
                                     Column("event_id", Integer,
                                            ForeignKey("events.id", onupdate="CASCADE", ondelete="CASCADE"),
                                            nullable=False),
                                     Column("user_id", Integer,
                                            ForeignKey("users.id", onupdate="CASCADE", ondelete="CASCADE"),
                                            nullable=False),
                                     )
'''

# TODO implementar mes endavant: implementar amb tornejos
'''
class Event(SQLAlchemyBase, JSONModel): #TODO modificar per als tornejos
    __tablename__ = "events"

    id = Column(Integer, primary_key=True)
    created_at = Column(DateTime, default=datetime.datetime.now, nullable=False)
    name = Column(Unicode(255), nullable=False)
    description = Column(UnicodeText)
    type = Column(Enum(EventTypeEnum))
    poster = Column(Unicode(255))
    start_date = Column(DateTime, nullable=False)
    finish_date = Column(DateTime, nullable=False)
    owner_id = Column(Integer, ForeignKey("users.id", onupdate="CASCADE", ondelete="CASCADE"), nullable=False)
    owner = relationship("User", back_populates="events_owner")
    registered = relationship("User", secondary=EventParticipantsAssociation, back_populates="events_enrolled")

    @hybrid_property
    def poster_url(self):
        return _generate_media_url(self, "poster", default_image=True)

    @hybrid_property
    def poster_path(self):
        return _generate_media_path(self, "poster")

    @hybrid_property
    def status(self):
        current_datetime = datetime.datetime.now()
        if current_datetime < self.start_date:
            return EventStatusEnum.open
        elif (current_datetime >= self.start_date) and (current_datetime <= self.finish_date):
            return EventStatusEnum.ongoing
        elif current_datetime > self.finish_date:
            return EventStatusEnum.closed
        else:
            return EventStatusEnum.undefined

    @status.expression
    def status(cls):
        current_datetime = datetime.datetime.now()
        return case(
            [
                (current_datetime < cls.start_date,
                 type_coerce(EventStatusEnum.open, Enum(EventStatusEnum))),
                (and_(current_datetime > cls.start_date, current_datetime < cls.finish_date),
                 type_coerce(EventStatusEnum.ongoing, Enum(EventStatusEnum))),
                (current_datetime > cls.finish_date,
                 type_coerce(EventStatusEnum.closed, Enum(EventStatusEnum))),
            ],
            else_=type_coerce(EventStatusEnum.undefined, Enum(EventStatusEnum))
        )

    #EVENTOS
    @hybrid_property
    def json_model(self):
        return {
            "id": self.id,
            "created_at": self.created_at.strftime(settings.DATETIME_DEFAULT_FORMAT),
            "name": self.name,
            "description": self.description,
            "poster_url": self.poster_url,
            "type": self.type.value,
            "start_date": self.start_date.strftime(settings.DATETIME_DEFAULT_FORMAT),
            "finish_date": self.finish_date.strftime(settings.DATETIME_DEFAULT_FORMAT),
            "owner": self.owner.username,
            "registered": [enrolled.username for enrolled in self.registered],
            "status": self.status.value
        }
'''


class UserToken(SQLAlchemyBase):
    __tablename__ = "users_tokens"

    id = Column(Integer, primary_key=True)
    token = Column(Unicode(50), nullable=False, unique=True)
    user_id = Column(Integer, ForeignKey("users.id", onupdate="CASCADE", ondelete="CASCADE"), nullable=False)


# Forums seguits TODO
'''

Following_Forums = Table("f_forums", SQLAlchemyBase.metadata,
                                     Column("user_id", Integer,
                                            ForeignKey("users.id", onupdate="CASCADE", ondelete="CASCADE"),
                                            nullable=False),
                                     Column("joc_id", Integer,
                                            ForeignKey("jocs.id", onupdate="CASCADE", ondelete="CASCADE"),
                                            nullable=False),

                                     )
'''
'''
Banned_Forums   = Table("b_forums", SQLAlchemyBase.metadata,
                                     Column("user_id", Integer,
                                            ForeignKey("users.id", onupdate="CASCADE", ondelete="CASCADE"),
                                            nullable=False),
                                     Column("joc_id", Integer,
                                            ForeignKey("jocs.id", onupdate="CASCADE", ondelete="CASCADE"),
                                            nullable=False),

                                     )
'''


# TODO afegir si l'user esta bloquejat (no bloquejat, bloqueig temporal, bloqueig permanent)
# TODO data en que la conta es desbloqueja
# USERS
class User(SQLAlchemyBase, JSONModel):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True)
    created_at = Column(DateTime, nullable=False, default=datetime.datetime.now)
    username = Column(Unicode(50), nullable=False, unique=True)
    account_type = Column(Enum(AccountTypeEnum), default=AccountTypeEnum.free)
    # following_forums = relationship("Jocs", secondary=Following_Forums, back_populates="seguint") #TODO no funciona
    # banned_forums = relationship("Jocs") #foros dels que estas banejat TODO no funciona
    # banned_users = relationship("User") #usuaris bloquejats TODO no funciona
    # friends = relationship("User") #amics todo no funciona
    # firends_request = relationship("User") #solicituds amics todo no funciona
    short_description = Column(Unicode(100), default="")  # OK
    long_description = Column(UnicodeText, default="")  # OK
    points = Column(Integer, default=0, nullable=True)  # OK
    password = Column(UnicodeText, nullable=False)
    email = Column(Unicode(255), nullable=False, unique=True)
    tokens = relationship("UserToken", cascade="all, delete-orphan")
    name = Column(Unicode(50), default="")
    surname = Column(Unicode(50), default="")
    birthday = Column(Unicode(10), nullable=False)  # es queda com a string pk aixi es pot fer tot desde java
    genere = Column(Enum(GenereEnum), default=GenereEnum.not_specified)
    # phone = Column(Unicode(50))
    photo = Column(Unicode(255), default="")  # TODO mirar si funciona
    recovery_code = Column(Unicode(6), nullable=True, unique=True)
    location = Column(Unicode(30), default="", nullable=True)  # OK
    tipo_de_jugador = Column(Enum(UserTypeEnum), default=UserTypeEnum.casual, nullable=True)

    # TODO implementar mes endavant: desactivat fins a implementar tornejos
    '''
    events_owner = relationship("Event", back_populates="owner", cascade="all, delete-orphan")
    events_enrolled = relationship("Event", back_populates="registered")
    '''

    @hybrid_property
    def public_profile(self):
        return {
            "created_at": self.created_at.strftime(settings.DATE_DEFAULT_FORMAT),
            "username": self.username,
            "longdesc": self.long_description,
            "shortdesc": self.short_description,
            "image": self.photo,
            "location": self.location,
        }

    # TODO mirar si funciona
    @hybrid_property
    def photo_url(self):
        return _generate_media_url(self, "photo")

    # TODO mirar si funciona
    @hybrid_property
    def photo_path(self):
        return _generate_media_path(self, "photo")

    @hybrid_method
    def set_password(self, password_string):
        self.password = pbkdf2_sha256.hash(password_string)

    @hybrid_method
    def check_password(self, password_string):
        return pbkdf2_sha256.verify(password_string, self.password)

    @hybrid_method
    def create_token(self):
        if len(self.tokens) < settings.MAX_USER_TOKENS:
            token_string = binascii.hexlify(os.urandom(25)).decode("utf-8")
            aux_token = UserToken(token=token_string, user_id=self.id)
            return aux_token
        else:
            raise falcon.HTTPBadRequest(title=messages.quota_exceded, description=messages.maximum_tokens_exceded)

    # TODO: comprovar
    '''
    banned_forums
    banned_users
    
    '''

    # TODO cambiar birthday a data
    @hybrid_property
    def json_model(self):
        return {
            "created_at": self.created_at.strftime(settings.DATETIME_DEFAULT_FORMAT),
            "username": self.username,
            "account_type": self.account_type.value,
            # "following_forums": [Jocs.name for _ in self.following_forums],
            # "banned_forums": self.banned_forums,
            # "banned_users": self.banned_users,
            # "friends": self.friends,
            # "friends_request": self.firends_request,
            "short_description": self.short_description,
            "long_description": self.long_description,
            "points": self.points,
            "password": self.password,
            "email": self.email,
            "name": self.name,
            "surname": self.surname,
            "birthday": self.birthday,
            "genere": self.genere.value,
            "photo": self.photo_url,
            "location": self.location,
            "tipo_jugador": self.tipo_de_jugador
        }


# TODO: implementar mes endavant: posts
'''
#POSTS
class Posts(SQLAlchemyBase, JSONModel): #TODO: acabar
    __tablename__ = "posts"

    id = Column(Integer, primary_key=True)
    published_at = Column(DateTime,default=datetime.datetime.now, nullable=False)
    publisher = relationship("User", back_populates="owner", cascade="all, delete-orphan")
    header = Column(Unicode(150), nullable=False)
    body = Column(UnicodeText, nullable=False)

    @hybrid_property
    def json_model(self):
        return {
            "id": self.id,
            "header": self.header,
            "body": self.body,
            "published_at": self.published_at,
            "publisher": self.publisher
        }
'''

# TODO implementar mes endavant: comments
'''
#Comments
class Comments(SQLAlchemyBase, JSONModel): #TODO: acabar
    __tablename__ = "comments"
    id = Column(Integer, primary_key=True)
    user_post = relationship("User",back_populates="g" , cascade="all, delete-orphan")#TODO: corretgir
    points = Column(Float, default=0 ,nullable=False)
    comment = Comments(UnicodeText)
'''


# Base de dades deles plataformes de videjocs #TODO: comprovar
class Platforms(SQLAlchemyBase, JSONModel):
    __tablename__ = "platforms"

    id = Column(Integer, primary_key=True)
    name = Column(Unicode(100), unique=True, nullable=False)
    manufacturer = Column(Unicode(100), nullable=False)

    def json_model(self):
        return {
            "id": self.id,
            "name": self.name,
            "manufacturer": self.manufacturer

        }


# Base de dades de les categories que hi ha
class Categories(SQLAlchemyBase, JSONModel):
    __tablename__ = "categories"
    id = Column(Integer, primary_key=True)
    name = Column(Unicode(30), unique=True, nullable=False)

    @hybrid_property
    def json_model(self):
        return {
            "id": self.id,
            "name": self.name
        }


# Base de dades dels jocs
class Jocs(SQLAlchemyBase, JSONModel):  # TODO: comprovar
    __tablename__ = "jocs"

    Categories_game = Table("cat_game", SQLAlchemyBase.metadata,
                            Column("game_id_cat_game", Integer,
                                   ForeignKey("jocs.id", onupdate="CASCADE", ondelete="CASCADE"),
                                   nullable=False),
                            Column("categories_cat_game", Integer,
                                   ForeignKey("categories.id", onupdate="CASCADE", ondelete="CASCADE"),
                                   nullable=False),

                            )

    Platforms_game = Table("plat-game", SQLAlchemyBase.metadata,
                           Column("game_id_plat_game", Integer,
                                  ForeignKey("jocs.id", onupdate="CASCADE", ondelete="CASCADE"),
                                  nullable=False),
                           Column("platform_plat_game", Integer,
                                  ForeignKey("platforms.id", onupdate="CASCADE", ondelete="CASCADE"),
                                  nullable=False),
                           )

    id = Column(Integer, primary_key=True)
    name = Column(Unicode(100), unique=True, nullable=False)
    categories = relationship("Categories", secondary=Categories_game)
    min_players = Column(Integer, default=1, nullable=False)
    max_players = Column(Integer, default=1, nullable=False)
    online_mode = Column(Boolean, default=False, nullable=False)
    published = Column(Unicode(10), default="", nullable=False)
    studio = Column(Unicode(100), nullable=False)
    image = Column(Unicode(255), default="")


    platforms = relationship("Platforms", secondary=Platforms_game)
    description = Column(UnicodeText, default="")
    pegi = Column(Integer, default=18, nullable=False)  # Edat recomanada
    aproved = Column(Boolean, default=False, nullable=False)

    @hybrid_property
    def json_model(self):
        return {
            "id": self.id,
            "name": self.name,
            "categories": [categories.name for categories in self.categories],
            "min_players": self.min_players,
            "max_players": self.max_players,
            "online_mode": self.online_mode,
            "published": self.published,
            "studio": self.studio,
            "image": self.image_url,
            "platforms": [platform.name for platform in self.platforms],
            "description": self.description,
            "pegi": self.pegi,
            "aproved": self.aproved
        }

    @hybrid_property
    def image_url(self):
        return _generate_media_url(self, "image")

    # TODO mirar si funciona
    @hybrid_property
    def image_path(self):
        return _generate_media_path(self, "image")


'''
#Base de dades dels modes de joc    #TODO: acabar
class Modesjocs(SQLAlchemyBase, JSONModel):
    __tablename__ = "modes_de_joc"
    id = Column(Integer, primary_key=True)
    nom_mode = Column(Unicode(100))
    joc_pare = relationship #todo
    nom_complert = Column(Unicode(210), unique=True, nullable=False)
'''

# TODO implementar mes endavant: crear model per a les tendes
# Base de dades per a les tendes
