package dam.agamers.gtidic.udl.agamers.views;

import dam.agamers.gtidic.udl.agamers.CommonActivity;


import android.os.Bundle;
import android.view.View;


import dam.agamers.gtidic.udl.agamers.R;

public class LogInActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici_de_sessio);
        getSupportActionBar().hide();
    }

    public void obre_registre (View view){
        goTo(SignUpActivity.class);
    }
}