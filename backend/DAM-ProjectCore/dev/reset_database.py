#!/usr/bin/python
# -*- coding: utf-8 -*-


import datetime
import logging
import os

import random as rand

from sqlalchemy.sql import text

import db
import settings
from db.models import SQLAlchemyBase, User, AccountTypeEnum, GenereEnum, Jocs, Platforms, Categories
from settings import DEFAULT_LANGUAGE

# LOGGING
mylogger = logging.getLogger(__name__)
settings.configure_logging()


def execute_sql_file(sql_file):
    sql_folder_path = os.path.join(os.path.dirname(__file__), "sql")
    sql_file_path = open(os.path.join(sql_folder_path, sql_file), encoding="utf-8")
    sql_command = text(sql_file_path.read())
    db_session.execute(sql_command)
    db_session.commit()
    sql_file_path.close()


if __name__ == "__main__":
    settings.configure_logging()

    db_session = db.create_db_session()

    # -------------------- REMOVE AND CREATE TABLES --------------------
    mylogger.info("Removing database...")
    SQLAlchemyBase.metadata.drop_all(db.DB_ENGINE)
    mylogger.info("Creating database...")
    SQLAlchemyBase.metadata.create_all(db.DB_ENGINE)

    # -------------------- CREATE USERS --------------------
    mylogger.info("Creating default users...")
    # noinspection PyArgumentList
    user_admin = User(
        created_at=datetime.datetime(2020, 2, 2, 0, 1, 1),
        username="admin",
        account_type=AccountTypeEnum.premium,
        email="admin@damcore.com",
        name="Administrator",
        surname="DamCore",
        birthday="hoy",
        genere=GenereEnum.not_specified
    )
    user_admin.set_password("DAMCoure")
    db_session.add(user_admin)

    '''
    user_1 = User(
        created_at=datetime.datetime(2020, 1, 1, 0, 1, 1),
        username="usuari1",
        pago=PagoTypeEnum.Freemium,
        email="usuari1@gmail.com",
        name="usuari",
        surname="1",
        birthdate=datetime.datetime(1989, 1, 1),
        genere=GenereEnum.male
    )
    user_1.set_password("a1s2d3f4")
    user_1.tokens.append(UserToken(token="656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf"))
    db_session.add(user_1)

    # noinspection PyArgumentList
    user_2 = User(
        created_at=datetime.datetime(2020, 1, 1, 0, 1, 1),
        username="user2",
        pago=PagoTypeEnum.Premium,
        email="user2@gmail.com",
        name="user",
        surname="2",
        birthdate=datetime.datetime(2017, 1, 1),
        genere=GenereEnum.male,
    )
    user_2.set_password("r45tgt")
    user_2.tokens.append(UserToken(token="0a821f8ce58965eadc5ef884cf6f7ad99e0e7f58f429f584b2"))
    db_session.add(user_2)

    # -------------------- CREATE USERS PREMIUM I FREMIUM --------------------
    for i in range(0, 10):
        f = str(i) + "usfree"

        # noinspection PyArgumentList
        user_free = User(
            username=f,
            pago=PagoTypeEnum.Freemium,
            email=f+"@gmail.com",
            name=f,
            surname="free",
            birthdate=datetime.datetime(1989, 1, 1),
            genere=GenereEnum.male
        )
        user_free.set_password(f+"pass")
        db_session.add(user_free)

        p = str(i) + "uspro"
        # noinspection PyArgumentList
        user_pre = User(
            username=p,
            pago=PagoTypeEnum.Premium,
            email=p+"@gmail.com",
            name=p+"user",
            surname="pro",
            birthdate=datetime.datetime(1989, 1, 1),
            genere=GenereEnum.male
        )
        user_pre.set_password(p+"pass")
        db_session.add(user_pre)
    # -------------------- CREATE EVENTS --------------------

    day_period = datetime.timedelta(days=1)

    event_lanparty = Event(
        created_at=datetime.datetime.now(),
        name="event3",
        description="desc3",
        type=EventTypeEnum.lanparty,
        start_date=datetime.datetime.now(),
        finish_date=datetime.datetime.now() + (day_period * 1),
        owner_id=1,
        registered=[]
    )
    db_session.add(event_lanparty)

    event_hackatoon = Event(
        created_at=datetime.datetime.now(),
        name="event1",
        description="description 1",
        type=EventTypeEnum.hackathon,
        start_date=datetime.datetime.now() + (day_period * 3),
        finish_date=datetime.datetime.now() + (day_period * 5),
        owner_id=0,
        poster="logo.png",
        registered=[user_1]
    )

    db_session.add(event_hackatoon)

    event_livecoding = Event(
        created_at=datetime.datetime.now(),
        name="event2",
        description="descr2",
        type=EventTypeEnum.livecoding,
        start_date=datetime.datetime.now() - (day_period * 5),
        finish_date=datetime.datetime.now() - (day_period * 4),
        owner_id=1,
        registered=[user_1,user_2]
    )
    db_session.add(event_livecoding)
    '''

    mylogger.info("Creating default categories...")

    categories = []
    c1 = Categories(

        id=1,
        name="Acció"

    )
    categories.append(c1)
    db_session.add(c1)

    c2 = Categories(

        id=2,
        name="Adventura i casual"

    )
    categories.append(c2)
    db_session.add(c2)

    c3 = Categories(

        id=3,
        name="Rol-playing"

    )
    categories.append(c3)
    db_session.add(c3)

    c4 = Categories(

        id=4,
        name="Simulació"

    )
    categories.append(c4)
    db_session.add(c4)

    c5 = Categories(

        id=5,
        name="Estràtegia"
    )
    categories.append(c5)
    db_session.add(c5)

    c6 = Categories(

        id=6,
        name="Esports i ràcing"
    )
    categories.append(c6)
    db_session.add(c6)


    mylogger.info("Creating default games...")

    jocs = []

    mylogger.info("Creating default platforms...")
    platforms = []
    p1 = Platforms(

        id=1,
        name="PS4",
        manufacturer="Sony"

    )
    platforms.append(p1)
    db_session.add(p1)

    p2 = Platforms(

        id=2,
        name="Xbox One",
        manufacturer="Microsoft"

    )
    platforms.append(p2)
    db_session.add(p2)

    p3 = Platforms(

        id=3,
        name="PS5",
        manufacturer="Sony"

    )
    platforms.append(p3)
    db_session.add(p3)

    p4 = Platforms(

        id=4,
        name="PS3",
        manufacturer="Sony"
    )
    platforms.append(p4)
    db_session.add(p4)

    p5 = Platforms(

        id=5,
        name="Nintendo Switch",
        manufacturer="Nintendo"
    )
    platforms.append(p5)
    db_session.add(p5)

    p6 = Platforms(

        id=6,
        name="Nintendo DS",
        manufacturer="Nintendo"
    )
    platforms.append(p6)
    db_session.add(p6)

    p7 = Platforms(

        id=7,
        name="Microsoft Windows",
        manufacturer= "Microsoft"
    )
    platforms.append(p7)
    db_session.add(p7)

    p8 = Platforms(

        id=8,
        name="MacOs",
        manufacturer="Apple"
    )
    platforms.append(p8)
    db_session.add(p8)

    for u in range(1, 5):
        joc = Jocs(

            id=u,

            name="Joc " + str(u),

            studio="",

            categories=rand.sample(categories, 3),

            platforms=rand.sample(platforms, 2)

        )

        jocs.append(joc)
        db_session.add(joc)

    db_session.commit()
    db_session.close()
