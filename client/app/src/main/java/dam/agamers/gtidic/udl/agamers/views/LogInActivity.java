package dam.agamers.gtidic.udl.agamers.views;

import dam.agamers.gtidic.udl.agamers.CommonActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.viewmodels.LogInViewModel;

public class LogInActivity extends CommonActivity {

    //components
    private LogInViewModel logInViewModel;
    private TextInputLayout comp_email_field;
    private TextInputLayout comp_password_field;
    
    private Button comp_login_button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici_de_sessio);
        getSupportActionBar().hide();
        InitView();
    }

    protected void InitView(){
        logInViewModel = new LogInViewModel();
        comp_email_field = this.findViewById(R.id.login_email_textinputlayout);
        comp_password_field = this.findViewById(R.id.contra_textinputlayout);

    }

    public void open_SingUp(View view){
        goTo(SignUpActivity.class);
    }

    public void login(View view){
        Log.d(TAG, "Entering login()...");

        String email = comp_email_field.getEditText().getText().toString();
        String password = comp_password_field.getEditText().getText().toString();

        //TODO validar i mostrar els errors pertinents

        logInViewModel.login(email,password);
    }

    public void mostrar_resposta(View view, String error){
        Snackbar snackbar;

        snackbar = Snackbar.make(view, error,5000);
        /*
        if (true){
            snackbar = Snackbar.make(view, getString(R.string.registre_ok), 5000);
        }
        else {
            snackbar = Snackbar.make(view, getString(R.string.registre_error), 10000);
        }
         */
        snackbar.show();
    }
}