package dam.agamers.gtidic.udl.agamers;

import android.content.Context;
import android.view.View;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.google.android.material.textfield.TextInputLayout;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.SignUpActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class Registre_test {
    @Rule
    public ActivityScenarioRule<SignUpActivity> mActivityRule = new ActivityScenarioRule<>(SignUpActivity.class);

    private Context context;

    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String err = error.toString();
                return expectedErrorText.equals(err);
            }
        };
    }

    @Before
    public void setUp() throws IOException {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void termsConditionDisplayedWhenClick() {
        onView(withId(R.id.termes)).perform(ViewActions.click());
        onView(withText(R.string.termes_i_condicions_text)).check(matches(isDisplayed()));
    }

    @Test
    public void nameInputDisplayErrorsWhenSpaceExists() {

        onView(withId(R.id.name_edit_text))
                .perform(typeText("Ramon "));

        onView(withId(R.id.name_textinputlayout))
                .check(matches(hasTextInputLayoutErrorText(
                        context.getResources().getString(R.string.error_nom_no_vàlid))));

    }

//    @Test
//    public void test_error_nom_numero(){
//        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
//                .perform(ViewActions.typeText("Ramon23"));
//        Espresso.onView(ViewMatchers.withId(R.id.name_textinputlayout))
//            .check(matches(che)));
//    }

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

