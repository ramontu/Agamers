#!/usr/bin/python
# -*- coding: utf-8 -*-
from db.models import User
from resources.base_resources import DAMCoreResource

user = [[8, 12], [6, 6], [9, 3], [10, 6], [14, 5]] #jocs en comú, diferencia d'edat
result=[0]*len(user)


class Resource_Recalculate_Matching_Score(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(Resource_Recalculate_Matching_Score, self).on_post(req, resp, *args, **kwargs)

        if "id" in kwargs:
            user_who_calculates = self.db_session.query(User).filter(User.id == kwargs["id"]).one()
            users = self.db_session.query(User).all()

            user_who_game_list = user_who_calculates.games_played

            llista_ids_user = []
            for i in user_who_game_list:
                llista_ids_user.append(i.id)

            for i in users:
                if i.id is not user_who_calculates.id:
                    llista_games_other = i.games_played
                    llista_ids_other = []
                    for o in llista_games_other:
                        llista_ids_other.append(o.id)
                    puntuacio = 0 #TODO

            print(llista_ids)



            '''
            result = [0] * len(user)
            for i in users:
                jocs_en_comu = user[i][0]
                dif_edat = user[i][1]
                if (dif_edat == 0):
                    dif_edat = 1
                n = jocs_encomu
                for in range(2):
                    jocs_en_comu = jocs_en_comu * n
                result[i] = jocs_en_comu / dif_edat
            '''