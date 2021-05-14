#!/usr/bin/python
# -*- coding: utf-8 -*-

SchemaUserToken = {
    "type": "object",
    "properties": {
        "token": {"type": "string"},
    },
    "required": ["token"]
}

SchemaRegisterUser = {
    "type": "object",
    "properties": {
        "username": {"type": "string"},
        "password": {"type": "string"},
        "email": {"type": "string"},
        "birthday": {"type": "string"}
    },
    "required": ["username", "password", "email", "birthday"]
}

SchemaUpdateUser = {
    "type": "object",
    "properties": {
        "username": {"type": "string"},
        "password": {"type": "string"},
        "account_type": {"type": "string"},
        "short_description": {"type": "string"},
        "long_description": {"type": "string"},
        "email": {"type": "string"},
        "name": {"type": "string"},
        "surname": {"type": "string"},
        "genere": {"type": "string"}
    }
}

SchemaNewGame = {
    "type": "object",
    "properties": {
        "name": {"type": "string"},
        "categories": {"type": "string"},  # TODO possiblement fallarà amb més de una
        "min_players": {"type": "Integer"},
        "max_players": {"type": "Integer"},
        "online_mode": {"type": "Boolean"},
        "published": {"type": "string"},
        "studio": {"type": "string"},
        "image": {"type": "string"},
        "platforms": {"type": "string"},  # TODO possiblement fallarà amb més de una
        "description": {"type": "string"},
        "pegi": {"type": "Integer"},
        "aproved": {"type": "Boolean"}
    },
    "required": ["name", "studio"]
}

SchemaUpdateGame = {
    "type": "object",
    "properties": {
        "name": {"type": "string"},
        "categories": {"type": "string"},  # TODO possiblement fallarà amb més de una
        "min_players": {"type": "Integer"},
        "max_players": {"type": "Integer"},
        "online_mode": {"type": "Boolean"},
        "published": {"type": "string"},
        "studio": {"type": "string"},
        "image": {"type": "string"},
        "platforms": {"type": "string"},  # TODO possiblement fallarà amb més de una
        "description": {"type": "string"},
        "pegi": {"type": "Integer"},
        "aproved": {"type": "Boolean"}
    }
}
