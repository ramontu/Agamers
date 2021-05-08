package dam.agamers.gtidic.udl.agamers;

import org.junit.Test;

import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorUnitTest {


    //USERNAME
    @Test
    public void username_test1_1(){
        assertTrue(AccountValidator.check_usernameValid("a1122344212332234323"));
    }
    //TODO POSAR NOMS DESCRIPTIUS A TOTS ELS TESTS
    @Test
    public void username_test1_2(){
        assertTrue(AccountValidator.check_usernameValid("ramon"));
    }

    @Test
    public void username_test1_3(){
        assertTrue(AccountValidator.check_usernameValid("ramon2001"));
    }

    @Test
    public void username_test1_4() { assertTrue(AccountValidator.check_usernameValid("200aa00")); }

    @Test
    public void username_test1_5(){
        assertFalse(AccountValidator.check_usernameValid(""));
    }

    @Test
    public void username_test1_6(){
        assertFalse(AccountValidator.check_usernameValid(""));
    }

    @Test
    public void username_test1_7(){
        assertTrue(AccountValidator.check_usernameValid("aGAMERS"));
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
    public void mail_test_1() { assertTrue(AccountValidator.check_mailValid("ramontu@gmail.com"));}

    @Test
    public void mail_test_2() { assertFalse(AccountValidator.check_mailValid("ramontugmail.com"));}

    @Test
    public void mail_test_3() { assertFalse(AccountValidator.check_mailValid("ramontu@gmailcom"));}

    @Test
    public void mail_test_4() { assertFalse(AccountValidator.check_mailValid("  ramontu@gmail.com"));}

    @Test
    public void mail_test_5() { assertFalse(AccountValidator.check_mailValid("ram   ontu@gmail.com"));}

    @Test
    public void mail_test_6() { assertFalse(AccountValidator.check_mailValid("  ramontu@gmail.com"));}

    @Test
    public void mail_test_7() { assertFalse(AccountValidator.check_mailValid("ram   ontu@gmail.com"));}

    //TEST NOM_COGNOM
    //DONE
    @Test
    public void nom_cog_test_1() { assertTrue(AccountValidator.check_nameOrSurnameValid("Ramon"));}

    @Test
    public void nom_cog_test_2() { assertTrue(AccountValidator.check_nameOrSurnameValid("Ramon Trilla"));} //NOM/COGNOM compost

    @Test
    public void nom_cog_test_3() { assertFalse(AccountValidator.check_nameOrSurnameValid("Ramo566732n"));}

    @Test
    public void nom_cog_test_4() { assertFalse(AccountValidator.check_nameOrSurnameValid("R@mon"));}

    @Test
    public void nom_cog_test_5() { assertFalse(AccountValidator.check_nameOrSurnameValid("Ramondavidsamanthaoriolramondavidsamanthaoriolramondavidsamanthaoriol"));}

    @Test
    public void nom_cog_test_6() { assertTrue(AccountValidator.check_nameOrSurnameValid(""));}

    @Test
    public void nom_cog_test_7() { assertTrue(AccountValidator.check_nameOrSurnameValid("Ramón"));}



}
