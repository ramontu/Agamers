package dam.agamers.gtidic.udl.agamers.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.R;

import dam.agamers.gtidic.udl.agamers.databinding.ActivitySignupBinding;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;
import dam.agamers.gtidic.udl.agamers.viewmodels.Registre_1ViewModel;

public class SignUpActivity extends AppCompatActivity {
    final String TAG = "Registre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        initView();
        set_accio_tornar_enrere();
    }

    private void initView(){
        Registre_1ViewModel registre1ViewModel = new ViewModelProvider(this).get(Registre_1ViewModel.class);

        ActivitySignupBinding activitySignupBinding =
                DataBindingUtil.setContentView(this,R.layout.activity_signup);
        activitySignupBinding.setLifecycleOwner(this);
        activitySignupBinding.setViewModel(registre1ViewModel);
    }

    private void set_accio_tornar_enrere(){
        //boto per a tornar a inici (el propi de android)
        //En teoria no ens cal
        /*
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
                finish();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this,callback);

         */
        checking();
    }

    //@Jordi -> poseu noms adients b_nom === isNameValid isPasswordValid -> sóc molt pesat amb
    // punyetetes ho sento!
    //TODO POSAR NOMS ADIENTS

    private boolean isNameValid = false;
    private boolean isSurnameValid = false;
    private boolean isPasswordValid = false;
    private boolean isSamePassword = false;
    private boolean isMailValid = false;
    private boolean isTermsValid = false;


    //TODO falta refactor
    /**
     * S'encarrega de fer totes les comprovacions necessaries per a que el registre es ralitzi de forma satisfactòria
     */
    protected void checking() {
        //Comprovació nom
        TextInputLayout nom = (TextInputLayout) findViewById(R.id.name_textinputlayout);
        nom.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!AccountValidator.check_nameOrSurnameValid(s.toString())){
                    nom.setError(getString(R.string.error_nom_no_vàlid));
                    isNameValid = false;
                }
                else {
                    nom.setError(null);
                    isNameValid = true;
                }
                tots_camps_valids();
            }
        });

        //Comprovació cognom
        TextInputLayout cognom = (TextInputLayout) findViewById(R.id.cognom_textinputlayout);
        cognom.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!AccountValidator.check_nameOrSurnameValid(s.toString())){
                    cognom.setError(getString(R.string.error_cognom_no_vàlid));
                    isSurnameValid = false;
                }
                else {
                    cognom.setError(null);
                    isSurnameValid = true;
                }
                tots_camps_valids();
            }
        });


        //comprovació contra
        TextInputLayout contra = (TextInputLayout) findViewById(R.id.contra_textinputlayout);
        contra.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                isPasswordValid = !AccountValidator.check_passwordValid(s.toString());
                updateForm(isMailValid, contra, getString(R.string.error_contra_no_vàlida));
            }
        });


        //comprovació confirmar contra
        TextInputLayout confi_contra = (TextInputLayout) findViewById(R.id.confirmar_contra_textinputlayout);
        confi_contra.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(String.valueOf(contra.getEditText().getText()))){
                    confi_contra.setError(getString(R.string.error_confirmar_contra));
                    isSamePassword = false;
                }
                else {
                    confi_contra.setError(null);
                    isSamePassword = true;
                }
                tots_camps_valids();

            }
        });

        //comprovació MAIL
        TextInputLayout email = (TextInputLayout) findViewById(R.id.email_textinputlayout);
        email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                isMailValid = AccountValidator.check_mailValid(s.toString());
                updateForm(isMailValid, email, getString(R.string.error_mail_no_vàlid));
            }
        });







    }

    //TODO POSAR EN UNA CLASSE APART
    /**
     * Es l'encarregat de que al donar-li al CheckBox de termes i condicions aparegui un diàleg on s'informa a l'usuari dels termes i condicions de l'app
     * @param view Pantalla actual
     */
    public void termes_condicions(View view){
        CheckBox term = findViewById(R.id.termes);

        if (term.isPressed()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.termes_i_condicions_text);
            builder.setTitle(R.string.termes_i_condicions_titol);
            builder.setPositiveButton(getString(R.string.terms_button_acceptar), (dialog, id) -> {
                CheckBox a = findViewById(R.id.termes);
                a.setChecked(true);
                isTermsValid = true;
                tots_camps_valids();
            });
            builder.setNegativeButton(getString(R.string.terms_button_declinar), (dialog, id) -> {
                CheckBox a = findViewById(R.id.termes);
                a.setChecked(false);
                isTermsValid = false;
                tots_camps_valids();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }


    /**
     * Comprova que tots els camps del formaulari de registre són correctes, de ser així permetrà a l'usuari premer el botó de registre
     */
    private void tots_camps_valids(){
        Button b =findViewById(R.id.boto_registrar);
        b.setEnabled(isNameValid && isSurnameValid && isPasswordValid && isSamePassword && isMailValid && isTermsValid);
    }

    public void missatge_registrat(View view){

        Snackbar snackbar;
        if (true){
            snackbar = Snackbar.make(view, getString(R.string.registre_ok), 5000);
        }
        else {
            snackbar = Snackbar.make(view, getString(R.string.registre_error), 10000);
        }
        snackbar.show();
    }


    //TODO JAVADOC
    private void updateForm(boolean isValid, TextInputLayout textInput, String error_msg) {
        if (!isValid) {
            textInput.setError(error_msg);
        } else {
            textInput.setError(null);
        }
        tots_camps_valids();
    }




}