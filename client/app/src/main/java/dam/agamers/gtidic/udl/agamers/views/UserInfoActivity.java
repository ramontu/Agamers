package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class UserInfoActivity extends CommonActivity {

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
        PreferencesProvider.providePreferences().edit().remove("token").apply();
        goTo(LogInActivity.class);
        finishAffinity();
    }
}