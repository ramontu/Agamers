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
        "name": {"type": "string"},
        "surname": {"type": "string"}
    },
    "required": ["username", "password", "email", "name", "surname"]
}

SchemaUpdateUser = {
    "type": "object",
    "properties": {
        "username": {"type": "string"},
        "password": {"type": "string"},
        "account_type": {"type": "string"},
        "email": {"type": "string"},
        "name": {"type": "string"},
        "surname": {"type": "string"},
        "genere": {"type": "string"}
    }
}
