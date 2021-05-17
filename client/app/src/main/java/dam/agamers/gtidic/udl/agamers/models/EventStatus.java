package dam.agamers.gtidic.udl.agamers.models;

import dam.agamers.gtidic.udl.agamers.R;

public enum EventStatus {

    O("O","open"),C("C","closed"),G("G","ongoing"), U("U", "undefined");

    String name;
    String id;

    EventStatus(String _id, String _name){

        id = _id;
        name = _name;
    }

    public String getName(){
        return name;
    }


    public static int getColourResource(EventStatus e){



        switch(e){

            case O:

                return R.color.soft_blue;

            case C:

                return R.color.ocean_blue;

            case G:

                return R.color.sailor_blue;

            default:

                return -1;

        }
    }
}
