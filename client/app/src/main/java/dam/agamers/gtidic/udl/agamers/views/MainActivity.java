package dam.agamers.gtidic.udl.agamers.views;


import android.os.Bundle;
import android.util.Log;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.viewmodels.MainActivityViewModel;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        PreferencesProvider.init(this);

        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();

        if (mainActivityViewModel.isCurrentLogIn()){
            Log.d(TAG, "onCreate() -> El usuari té un token, redirigint a pantalla principal");
            goTo(FirstActivity.class);
        }
        else{
            goTo(LogInActivity.class);
        }
    }
}

/*

}
 */