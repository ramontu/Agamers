package dam.agamers.gtidic.udl.agamers;
import android.view.View;

import dam.agamers.gtidic.udl.agamers.views.Registre_1;


import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.Wrapper;

import static androidx.test.espresso.assertion.ViewAssertions.matches;


@RunWith(AndroidJUnit4.class)
public class Registre_test {
    @Rule
    public ActivityTestRule<Registre_1> mActivityRule = new ActivityTestRule<>(Registre_1.class);

    @Before
    public void setUp(){
        mActivityRule.getActivity();
    }

    //TODO
    /* NO HEM TROBAT CAP MANERA QUE FES QUE L'ESPRESSO FUNCIONES AMB ELS TEXTINPUTLAYOUT PER A DETECTAR ELS ERRORS QUE SURTEN A SOTA
    @Test
    public void test_termes(){
        Espresso.onView(ViewMatchers.withId(R.id.termes)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText(R.string.termes_i_condicions_text));
    }

    @Test
    public void test_error_nom_espai(){
        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
                .perform(ViewActions.typeText("Ramon "));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.name_textinputlayout)
                , ViewMatchers.withText(R.string.error_nom_no_vàlid)));

    }

    @Test
    public void test_error_nom_numero(){
        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
                .perform(ViewActions.typeText("Ramon23"));
        Espresso.onView(ViewMatchers.withId(R.id.name_textinputlayout))
            .check(matches(che)));
    }

    @Test
    public void test_error_cognom_espai(){
        Espresso.onView(ViewMatchers.withId(R.id.cognom_edit_text))
                .perform(ViewActions.typeText(" Trilla "));
        Espresso.onView(ViewMatchers.withText(R.string.error_mail_no_vàlid));
    }




    Hem provat les principals solucions que hi ha a internet i no funciona cap
    @Test

    public void test_prova(){
        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
                .perform(ViewActions.typeText("Ramon"));

        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
            .check(ViewAssertions.matches(ViewMatchers.withHint("Nom*")));
    }

    @Test
    public void test_nom_incorrecte(){
        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
                .perform(ViewActions.typeText("Ramon "));

        Espresso.onView(ViewMatchers.withId(R.id.name_textinputlayout))
                .check(matches(hasTextInputLayoutHintText(mActivityRule.getActivity().getString(R.string.error_nom_no_vàlid))));
    }

    */


}



