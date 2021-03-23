package dam.agamers.gtidic.udl.agamers;

import dam.agamers.gtidic.udl.agamers.views.SignUpActivity;


import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


@RunWith(AndroidJUnit4.class)
public class Registre_test {
    @Rule
    public ActivityScenarioRule<SignUpActivity> mActivityRule = new ActivityScenarioRule<>(SignUpActivity.class);

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

//    @Test
//    public void test_error_nom_numero(){
//        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
//                .perform(ViewActions.typeText("Ramon23"));
//        Espresso.onView(ViewMatchers.withId(R.id.name_textinputlayout))
//            .check(matches(che)));
//    }

    @Test
    public void test_error_cognom_espai(){
        Espresso.onView(ViewMatchers.withId(R.id.cognom_edit_text))
                .perform(ViewActions.typeText(" Trilla "));
        Espresso.onView(ViewMatchers.withText(R.string.error_mail_no_vàlid));
    }


//    @Test
//    public void test_prova(){
//        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
//                .perform(ViewActions.typeText("Ramon"));
//
//        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
//            .check(ViewAssertions.matches(ViewMatchers.withHint("Nom*")));
//    }
//
//    @Test
//    public void test_nom_incorrecte(){
//        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
//                .perform(ViewActions.typeText("Ramon "));
//
//        Espresso.onView(ViewMatchers.withId(R.id.name_textinputlayout))
//                .check(matches(hasTextInputLayoutHintText(mActivityRule.getActivity().getString(R.string.error_nom_no_vàlid))));
//    }




}



