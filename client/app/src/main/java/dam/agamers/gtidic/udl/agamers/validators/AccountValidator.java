package dam.agamers.gtidic.udl.agamers.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 */
public class AccountValidator {

    //TODO posar condicions de validació al javadoc

    /**
     * Comprova que el username contingui minuscules, no contingui majuscules i no contingui caracters especials esxcepte "_" i "."
     * @param username String: Nom d'usuari que es vol comprovar
     * @return És viable o no
     * TODO falta fer la comprovació amb el client per a veure si ja existeix l'usuari
     */
    public static boolean check_usernameValid(String username){
        if (username.length() > 3 && username.length() < 21){
            if (patternIsValid(username, ".*[a-z].*")){
                if (patternIsValid(username,".*[A-Z].*")){
                    return false;
                }

                //Detecta maj caracteres especiales (excepte "_" i ".")
                return !patternIsValid(username, ".*[^a-z\\d:(?!_.)].*");
            }
            return false;
        }
        return false;

    }

    /**
     * Comprova que la contrasenya sigui vàlida (ha de contenir com a minim 3 coses (minus, majus, numeros, [!@#$%^&*]))
     * @param contrasenya String: Contrasenya que es vol comprovar
     * @return Retorna si es viable
     */
    public static boolean check_passwordValid(String contrasenya){
        int comptador = 0;

        if ((contrasenya.length() >=8) && (contrasenya.length() < 21)){

            //conte numeros
            if (patternIsValid(contrasenya, ".*[0-9].*")){
                comptador++;
            }
            //conte minuscules
            if (patternIsValid(contrasenya, ".*[a-z].*")){
                comptador++;
            }
            //conte MAJUSCULES
            if (patternIsValid(contrasenya,".*[A-Z].*")){
                comptador++;
            }
            //conté caracters especials
            if (patternIsValid(contrasenya, ".*[!@#$%^&*].*")){
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
    public static boolean check_mailValid(String mail){
        if (mail.length() > 40){
            return false;
        }
        return patternIsValid(mail, "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$");
    }

    /**
     * TODO Falta posar limit de caràcters
     * Comprova que el nom o cognom siguin viables
     * @param nom_cognom String: nom o cognom que es vol comprovar
     * @return Retorna si es viable o no
     */
    public static boolean check_nameOrSurnameValid(String nom_cognom){
        int comptador = 0;
        //màxim 40 caràcters
        if (nom_cognom.length() > 40){
            return false;
        }
        //TODO IMPLEMENTAR AUTOMATICAMENT
        //Si no te min o maj salta
        /*
        if (!comprovar_patro(nom_cognom,".*[a-z].*" ) || !comprovar_patro(nom_cognom,".*[A-Z].*" )){
            return false;
        }
         */
        //si tenim nums salta
        if (patternIsValid(nom_cognom, ".*[0-9].*")){
            return false;
        }
        //espais tabs etc al principi de linia no vàlids
        if (patternIsValid(nom_cognom, "^\\s+|\\s+$")){
            return false;
        }

        //caracters especials
        return !patternIsValid(nom_cognom, "[^a-zA-Z\\d\\s:]");
    }

    /**
     * Comprova que el parametre regex es trobia a entrada
     * @param entrada String a la que volem comprovar el patró
     * @param patro String: que conté el patró
     * @return Retorna si el patró es troba en la entrada o no
     */
    private static boolean patternIsValid(String entrada, String patro){
        Pattern pat = Pattern.compile(patro);
        Matcher mat = pat.matcher(entrada);
        return mat.find();
    }


}
