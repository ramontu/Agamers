#!/usr/bin/python
# -*- coding: utf-8 -*-


import datetime
import logging
import os

import random as rand
import string
from sqlalchemy.sql import text

import db
import settings
from db.models import SQLAlchemyBase, User, UserToken, AccountTypeEnum, GenereEnum, Jocs, Platforms, Categories, Matching_data
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


    # # noinspection PyArgumentList
    # user_admin = User(
    #     created_at=datetime.datetime(2020, 2, 2, 0, 1, 1),
    #     username="admin",
    #     account_type=AccountTypeEnum.premium,
    #     email="admin@damcore.com",
    #     name="Administrator",
    #     surname="DamCore",
    #     genere=GenereEnum.male,
    # )
    # user_admin.set_password("admin")
    # db_session.add(user_admin)



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

    # -------------------- CREATE USERS --------------------
    mylogger.info("Creating default users...")
    users = []
    for i in range(0, 10):
        f = str(i) + "usfree"

        # noinspection PyArgumentList
        user_free = User(
            username=f,
            email=f + "@gmail.com",
            name=f,
            surname="free",
            birthday=datetime.datetime(rand.randint(1980,2006), 1, 1),
            genere=GenereEnum.male,
            games= rand.sample(jocs, rand.randint(1,3))
        )
        user_free.set_password(f + "pass")
        user_free.tokens.append(UserToken(token=''.join([rand.choice( string.ascii_letters + string.digits) for n in range(50)])))
        db_session.add(user_free)
        users.append(user_free)
        db_session.commit()


    # -------------------- CREATE DEFAULT MATCHS --------------------

    # Fer en un classe la funcio que ara hi ha a test
    # recalculate(current_user) -> quan detectis que un usuari actualitzi el seu perfil modificant els jocs
    # cridar a aquesta funcion (hauars d'importar la classe)
    # for user in users: recalculate(user)



    db_session.commit()


    db_session.close()
