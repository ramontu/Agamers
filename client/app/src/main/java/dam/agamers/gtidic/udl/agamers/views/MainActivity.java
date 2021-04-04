package dam.agamers.gtidic.udl.agamers.views;


import android.os.Bundle;
import android.util.Log;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.viewmodels.MainActivityViewModel;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesProvider.init(this);

        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();

        if (mainActivityViewModel.isCurrentLogIn()){
            Log.d(TAG, "onCreate() -> El usuari té un tocken, redirigint a pantalla principal");
            //goTo();
        }
        else{
            goTo(LogInActivity.class);
        }


        //TODO fer que es redireccioni a la principal o a login si te token o no

    }
}

/*

}
 */