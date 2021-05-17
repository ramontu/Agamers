package dam.agamers.gtidic.udl.agamers.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dam.agamers.gtidic.udl.agamers.models.Account;

/**
 * @version 1.0
 */
public class AccountValidator {

    //TODO posar condicions de validació al javadoc

    /**
     * Comprova que el username pugui contenir minuscules, majuscules, numeros i "." i "_"
     * @param username String: Nom d'usuari que es vol comprovar
     * @return És viable o no
     * TODO falta fer la comprovació amb el client per a veure si ja existeix l'usuari
     */
    public static boolean check_usernameValid(String username){
        return patternIsValid(username, "^[a-zA-Z0-9._]{3,20}");
    }

    /**
     * Comprova que la contrasenya sigui vàlida (ha de contenir com a minim 3 coses (minus, majus, numeros))
     * @param contrasenya String: Contrasenya que es vol comprovar
     * @return Retorna si es viable
     */
    public static boolean check_passwordValid(String contrasenya){
        return patternIsValid(contrasenya, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
    }

    /**
     * Comprova que el correu electronic cumpleixi amb l'estandar (algo@algo.algo)
     * @param mail String: Mail que es vol comprovar
     * @return Retorna si es viable
     */
    public static boolean check_mailValid(String mail){
        return patternIsValid(mail, "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$");
    }

    /**
     * Comprova que el nom o cognom siguin viables (poden tenir minuscules, majuscules i accents i poden estar buides)
     * @param nom_cognom String: nom o cognom que es vol comprovar
     * @return Retorna si es viable o no
     */
    public static boolean check_nameOrSurnameValid(String nom_cognom){
        return patternIsValid(nom_cognom, "[A-Za-zÀ-Ÿà-ÿ]{0,18}(\\s){0,1}[A-Za-zÀ-Ÿà-ÿ]{0,18}$");
    }

    public static boolean check_recoverycodeValid(String code){
        return patternIsValid(code, "[A-Za-z0-9]{6}");
    }

    public static boolean check_longDescriptionValid(String long_description){
        return long_description.length() <=1000;
    }

    /**
     * Comprova que el parametre regex es trobia a entrada
     * @param entrada String a la que volem comprovar el patró
     * @param patro String: que conté el patró
     * @return Retorna si el patró es troba en la entrada o no
     */
    private static boolean patternIsValid(String entrada, String patro){
        return Pattern.matches(patro,entrada);
    }

    /*
    public static boolean check_account(Account account){
        return check_usernameValid(account.getUsername()) &&
                check_mailValid(account.getEmail()) &&
                check_nameOrSurnameValid(account.getName()) &&
                check_nameOrSurnameValid(account.getSurname()) &&
                ch
    }

     */


}