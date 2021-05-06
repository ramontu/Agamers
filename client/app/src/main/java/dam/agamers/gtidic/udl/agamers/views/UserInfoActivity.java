package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.viewmodels.UserInfoViewModel;

public class UserInfoActivity extends CommonActivity {

    UserInfoViewModel userInfoViewModel = new UserInfoViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().hide();
    }

    public void open_edit_info(View view){
        goTo(EditAccountActivity.class);
    }

    public void close_session(View view){
        userInfoViewModel.close_session();
        finish();
        goTo(LogInActivity.class);
        finishAffinity();
    }

    public void delete_account (View view){
        userInfoViewModel.delete_account();
        String response = getString(userInfoViewModel.getmResponseDeleteAccount().getValue());
        crear_snackbar(response,view);
        if (!response.contains("Error:")){
            goTo(LogInActivity.class);
            finishAffinity();
        }
    }

    public void crear_snackbar(String in, View view){
        Snackbar snackbar = Snackbar.make(view,in,10000);
        snackbar.show();
    }
}