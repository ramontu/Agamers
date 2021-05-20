package dam.agamers.gtidic.udl.agamers.validators;
import java.util.regex.Pattern;
import dam.agamers.gtidic.udl.agamers.models.Jocs;


public class JocsValidator {

    public static boolean check_nameOrStudioValid(String name_studio){
        return patternIsValid(name_studio,"^[a-zA-Z0-9._]{3,20}" );
    }

    public static boolean check_min_playersValid(int min_players){
        return patternIsValid(String.valueOf(min_players), "^[1-9][0-9]{0,3}");
    }

    public static boolean check_max_playersValid(int max_players){
        return patternIsValid(String.valueOf(max_players), "^[1-9][0-9]{0,3}");
    }

    public static boolean check_descriptionValid(String description){
        return patternIsValid(description, "^[a-zA-Z0-9._]{20,1000}");
    }

    public static boolean check_pegiValid(int pegi){
        return patternIsValid(String.valueOf(pegi), "^(([0-1]{0,1}[1-8]{1})|[9])$");
    }

    /**
     * Comprova que el parametre regex es trobia a entrada
     * @param input String a la que volem comprovar el patró
     * @param pattern String: que conté el patró
     * @return Retorna si el patró es troba en la entrada o no
     */
    private static boolean patternIsValid(String input, String pattern){
        return Pattern.matches(pattern,input);
    }


}
