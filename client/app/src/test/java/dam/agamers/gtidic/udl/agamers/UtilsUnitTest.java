package dam.agamers.gtidic.udl.agamers;

import org.junit.Test;

import dam.agamers.gtidic.udl.agamers.user.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsUnitTest {


    //USERNAME
    @Test
    public void username_test1_1(){
        assertTrue(Utils.comprovar_username("a1122344212332234323"));
    }

    @Test
    public void username_test1_2(){
        assertTrue(Utils.comprovar_username("ramon"));
    }

    @Test
    public void username_test1_3(){
        assertTrue(Utils.comprovar_username("ramon2001"));
    }

    @Test
    public void username_test1_4() { assertTrue(Utils.comprovar_username("200aa00")); }

    @Test
    public void username_test1_5(){
        assertFalse(Utils.comprovar_username(""));
    }

    @Test
    public void username_test1_6(){
        assertFalse(Utils.comprovar_username(""));
    }

    @Test
    public void username_test1_7(){
        assertFalse(Utils.comprovar_username("aGAMERS"));
    }

    //TEST CONTRASSENYA
    //DONE

    @Test
    public void contra_test2_1() { assertTrue(Utils.comprovar_contrasenya("aCb123456"));}

    @Test
    public void contra_test2_2() { assertTrue(Utils.comprovar_contrasenya("1234AbcD"));}

    @Test
    public void contra_test2_3() { assertFalse(Utils.comprovar_contrasenya("AcbAcbAcb"));}

    @Test
    public void contra_test2_4() { assertFalse(Utils.comprovar_contrasenya("acb123456"));}

    @Test
    public void contra_test2_5() { assertFalse(Utils.comprovar_contrasenya("aBc4567"));}

    @Test
    public void contra_test2_6() { assertFalse(Utils.comprovar_contrasenya("12345678"));}

    @Test
    public void contra_test2_7() { assertFalse(Utils.comprovar_contrasenya("12aa"));}

    @Test
    public void contra_test2_8() { assertFalse(Utils.comprovar_contrasenya("aaaa"));}

    @Test
    public void contra_test2_9() { assertFalse(Utils.comprovar_contrasenya("1234"));}

    @Test
    public void contra_test2_10() { assertTrue(Utils.comprovar_contrasenya("aCb123456@"));}

    @Test
    public void contra_test2_11() { assertTrue(Utils.comprovar_contrasenya("aCbhgahgjhik@"));}


    //TEST MAIL
    //DONE
    @Test
    public void mail_test_1() { assertTrue(Utils.comprovar_mail("ramontu@gmail.com"));}

    @Test
    public void mail_test_2() { assertFalse(Utils.comprovar_mail("ramontugmail.com"));}

    @Test
    public void mail_test_3() { assertFalse(Utils.comprovar_mail("ramontu@gmailcom"));}

    @Test
    public void mail_test_4() { assertFalse(Utils.comprovar_mail("  ramontu@gmail.com"));}

    @Test
    public void mail_test_5() { assertFalse(Utils.comprovar_mail("ram   ontu@gmail.com"));}

    @Test
    public void mail_test_6() { assertFalse(Utils.comprovar_mail("  ramontu@gmail.com"));}

    @Test
    public void mail_test_7() { assertFalse(Utils.comprovar_mail("ram   ontu@gmail.com"));}

    //TEST NOM_COGNOM
    //DONE
    @Test
    public void nom_cog_test_1() { assertTrue(Utils.comprovar_nom_o_cognom("Ramon"));}

    @Test
    public void nom_cog_test_2() { assertTrue(Utils.comprovar_nom_o_cognom("Ramon Trilla"));} //NOM/COGNOM compost

    @Test
    public void nom_cog_test_3() { assertFalse(Utils.comprovar_nom_o_cognom("Ramo566732n"));}

    @Test
    public void nom_cog_test_4() { assertFalse(Utils.comprovar_nom_o_cognom("R@mon"));}

    @Test
    public void nom_cog_test_5() { assertFalse(Utils.comprovar_nom_o_cognom("Ramondavidsamanthaoriolramondavidsamanthaoriolramondavidsamanthaoriol"));}



}
