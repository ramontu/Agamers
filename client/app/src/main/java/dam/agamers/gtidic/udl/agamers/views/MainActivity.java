package dam.agamers.gtidic.udl.agamers.views;


import android.os.Bundle;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesProvider.init(this);
        goTo(LogInActivity.class);

        //TODO fer que es redireccioni a la principal o a login si te token o no
    }
}

/*

}
 */