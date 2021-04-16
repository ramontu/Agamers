package dam.agamers.gtidic.udl.agamers.views;

import dam.agamers.gtidic.udl.agamers.CommonActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.viewmodels.LogInViewModel;

public class LogInActivity extends CommonActivity {

    //components
    private LogInViewModel logInViewModel;
    private TextInputLayout comp_email_field;
    private TextInputLayout comp_password_field;
    
    private Button comp_login_button;

    private String response_message;





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

        logInViewModel.getResponseLogin().observe(this, s -> {
            if (s.contains("Error: ")){
                mostrar_resposta(getCurrentFocus(), getString(R.string.login_error));
            }
            else {
                mostrar_resposta(getCurrentFocus(), getString(R.string.login_succes));
                goTo(FirstActivity.class);
            }
        });

    }


    public void open_SingUp(View view){
        goTo(SignUpActivity.class);
    }


    public void login(View view){
        Log.d(TAG, "Entering login()...");

        String email = comp_email_field.getEditText().getText().toString();
        String password = comp_password_field.getEditText().getText().toString();

        //TODO Validacions

        logInViewModel.login(email,password);
    }

    public void mostrar_resposta(View view, String string){
        Snackbar snackbar;
        snackbar = Snackbar.make(view, string,5000);
        snackbar.show();
    }
}