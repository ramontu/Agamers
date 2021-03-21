package dam.agamers.gtidic.udl.agamers.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 */
public class Utils {

    /**
     * Comprova que el username cumpleix ems els paràmetres establerts
     * @param username String: Nom d'usuari que es vol comprovar
     * @return És viable o no
     * TODO falta fer la comprovació amb el client per a veure si ja existeix l'usuari
     */
    public static boolean comprovar_username(String username){
        if (username.length() > 3 && username.length() < 21){
            if (comprovar_patro(username, ".*[a-z].*")){
                if (comprovar_patro(username,".*[A-Z].*")){
                    return false;
                }

                //Detecta maj caracteres especiales (excepte "_" i ".")
                if (comprovar_patro(username, ".*[^a-z\\d:(?!_.)].*")){
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;

    }

    /**
     * Comprova que la contrasenya sigui vàlida (ha de contenir per lo menos 3 coses (minus, majus, numeros, [!@#$%^&*]))
     * @param contrasenya String: Contrasenya que es vol comprovar
     * @return Retorna si es viable
     */
    public static boolean comprovar_contrasenya(String contrasenya){
        int comptador = 0;

        if ((contrasenya.length() >=8) && (contrasenya.length() < 21)){

            //conte numeros
            if (comprovar_patro(contrasenya, ".*[0-9].*")){
                comptador++;
            }
            //conte minuscules
            if (comprovar_patro(contrasenya, ".*[a-z].*")){
                comptador++;
            }
            //conte MAJUSCULES
            if (comprovar_patro(contrasenya,".*[A-Z].*")){
                comptador++;
            }
            //conté caracters especials
            if (comprovar_patro(contrasenya, ".*[!@#$%^&*].*")){
                comptador++;
            }
        }
        return comptador >= 3;
    }

    /**
     * Comprova que el correu electronic cumpleixi amb l'estandar (algo@algo.algo)
     * @param mail String: Mail que es vol comprovar
     * @return Retorna si es viable
     */
    public static boolean comprovar_mail(String mail){
        if (mail.length() > 40){
            return false;
        }
        return comprovar_patro(mail, "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$");
    }

    /**
     * TODO Falta posar limit de caràcters
     * Comprova que el nom o cognom siguin viables
     * @param nom_cognom String: nom o cognom que es vol comprovar
     * @return Retorna si es viable o no
     */
    public static boolean comprovar_nom_o_cognom(String nom_cognom){
        int comptador = 0;
        //màxim 40 caràcters
        if (nom_cognom.length() > 40){
            return false;
        }
        //Si no te min o maj salta
        if (!comprovar_patro(nom_cognom,".*[a-z].*" ) || !comprovar_patro(nom_cognom,".*[A-Z].*" )){
            return false;
        }
        //si tenim nums salta
        if (comprovar_patro(nom_cognom, ".*[0-9].*")){
            return false;
        }
        //espais tabs etc al principi de linia no vàlids
        if (comprovar_patro(nom_cognom, "^\\s+|\\s+$")){
            return false;
        }

        //caracters especials
        if (comprovar_patro(nom_cognom, "[^a-zA-Z\\d\\s:]")){
            return false;
        }
        return true;
    }

    /**
     * Comprova que el parametre regex es trobia a entrada
     * @param entrada String a la que volem comprovar el patró
     * @param patro String: que conté el patró
     * @return Retorna si el patró es troba en la entrada o no
     */
    private static boolean comprovar_patro(String entrada, String patro){
        Pattern pat = Pattern.compile(patro);
        Matcher mat = pat.matcher(entrada);
        return mat.find();
    }
}
