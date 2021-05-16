package dam.agamers.gtidic.udl.agamers.models;

import dam.agamers.gtidic.udl.agamers.R;

public enum EventType {

    H("H","hackathon"),LP("LP","lanparty"),LC("LC","livecoding");

    String name;

    String id;



    EventType(String _id, String _name){

        id = _id;

        name = _name;

    }

    public static int getImageResource(EventType e){

        switch(e){

            case H:

                return R.drawable.list_icon;

            case LP:

                return R.drawable.chat_icon;

            case LC:

                return R.drawable.user_imagen;

            default:

                return -1;
        }

    }
}