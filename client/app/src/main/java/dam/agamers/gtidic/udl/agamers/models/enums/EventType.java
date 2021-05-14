package dam.agamers.gtidic.udl.agamers.models.enums;

import dam.agamers.gtidic.udl.agamers.R;

public enum EventType{
    H("H","hackathon"),LP("LP","lanparty"),LC("LC","livecoding");

    public String name;
    public String id;

    EventType(String _id, String _name){
        id = _id;
        name = _name;
    }

    public static int getImageResource(EventType e){

        switch(e){
            case H:
                return R.drawable.joc_icon;
            case LP:
                return R.drawable.list_icon;
            case LC:
                return R.drawable.cor;
            default:
                return -1;
        }
    }
}
