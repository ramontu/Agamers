package dam.agamers.gtidic.udl.agamers;

import org.junit.Test;

import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorUnitTest {


    //USERNAME
    @Test
    public void username_numbers(){
        assertTrue(AccountValidator.check_usernameValid("1122344212332234323"));
    }
    //TODO POSAR NOMS DESCRIPTIUS A TOTS ELS TESTS
    @Test
    public void username_lowercase(){
        assertTrue(AccountValidator.check_usernameValid("ramon"));
    }

    @Test
    public void username_lowercase_numbers(){
        assertTrue(AccountValidator.check_usernameValid("agamers123"));
    }

    @Test
    public void username_lowercase_between_numbers() {
        assertTrue(AccountValidator.check_usernameValid("200aa00"));
    }

    @Test
    public void username_not_enought_chacarcters(){
        assertFalse(AccountValidator.check_usernameValid(""));
    }

    @Test
    public void username_lowercase_uppercase(){
        assertTrue(AccountValidator.check_usernameValid("aGAMERS"));
    }

    @Test
    public void username_lower_upper_numbers(){
        assertTrue(AccountValidator.check_usernameValid("aGAMERS123"));
    }

    @Test
    public void username_lower_upper_special_caracters_numbers(){
        assertTrue(AccountValidator.check_usernameValid("aGAMERS123._"));
    }

    //TEST CONTRASSENYA
    //DONE

    @Test
    public void contra_test2_1() { assertTrue(AccountValidator.check_passwordValid("aCb123456"));}

    @Test
    public void contra_test2_2() { assertTrue(AccountValidator.check_passwordValid("1234AbcD"));}

    @Test
    public void contra_test2_3() { assertFalse(AccountValidator.check_passwordValid("AcbAcbAcb"));}

    @Test
    public void contra_test2_4() { assertFalse(AccountValidator.check_passwordValid("acb123456"));}

    @Test
    public void contra_test2_5() { assertFalse(AccountValidator.check_passwordValid("aBc4567"));}

    @Test
    public void contra_test2_6() { assertFalse(AccountValidator.check_passwordValid("12345678"));}

    @Test
    public void contra_test2_7() { assertFalse(AccountValidator.check_passwordValid("12aa"));}

    @Test
    public void contra_test2_8() { assertFalse(AccountValidator.check_passwordValid("aaaa"));}

    @Test
    public void contra_test2_9() { assertFalse(AccountValidator.check_passwordValid("1234"));}

    @Test
    public void contra_test2_10() { assertTrue(AccountValidator.check_passwordValid("aCb123456@"));}

    @Test
    public void contra_test2_11() { assertFalse(AccountValidator.check_passwordValid("aCbhgahgjhik@"));}


    //TEST MAIL
    //DONE
    @Test
    public void mail_test_1() { assertTrue(AccountValidator.check_mailValid("agamers@gmail.com"));}

    @Test
    public void mail_test_2() { assertFalse(AccountValidator.check_mailValid("agamersgmail.com"));}

    @Test
    public void mail_test_3() { assertFalse(AccountValidator.check_mailValid("agamers@gmailcom"));}

    @Test
    public void mail_test_4() { assertFalse(AccountValidator.check_mailValid("  agamers@gmail.com"));}

    @Test
    public void mail_test_5() { assertFalse(AccountValidator.check_mailValid("aga   mers@gmail.com"));}

    @Test
    public void mail_test_6() { assertFalse(AccountValidator.check_mailValid("  agamers@gmail.com"));}

    @Test
    public void mail_test_7() { assertFalse(AccountValidator.check_mailValid("aga   mers@gmail.com"));}

    //TEST NOM_COGNOM
    //DONE
    @Test
    public void nom_cog_test_1() { assertTrue(AccountValidator.check_nameOrSurnameValid("Agamers"));}

    @Test
    public void nom_cog_test_2() { assertTrue(AccountValidator.check_nameOrSurnameValid("Anoia Gamers"));} //NOM/COGNOM compost

    @Test
    public void nom_cog_test_3() { assertFalse(AccountValidator.check_nameOrSurnameValid("Aga566732mers"));}

    @Test
    public void nom_cog_test_4() { assertFalse(AccountValidator.check_nameOrSurnameValid("Ag@mers"));}

    @Test
    public void nom_cog_test_5() { assertFalse(AccountValidator.check_nameOrSurnameValid("AgamersAgamersAgamersAgamersAgamersAgamersAgamersAgamers"));}

    @Test
    public void nom_cog_test_6() { assertTrue(AccountValidator.check_nameOrSurnameValid(""));}

    @Test
    public void nom_cog_test_7() { assertTrue(AccountValidator.check_nameOrSurnameValid("Ägàmérs"));}



}
