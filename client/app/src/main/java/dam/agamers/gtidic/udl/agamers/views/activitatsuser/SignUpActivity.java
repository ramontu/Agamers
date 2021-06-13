package dam.agamers.gtidic.udl.agamers.views.activitatsuser;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;

import dam.agamers.gtidic.udl.agamers.databinding.ActivitySignupBinding;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;
import dam.agamers.gtidic.udl.agamers.viewmodels.SignUpViewModel;

public class SignUpActivity extends CommonActivity {
    final String TAG = "SignUp";
    SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        initView();
        checking();
        missatge_registrat();
    }

    private void initView(){
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        ActivitySignupBinding activitySignupBinding =
                DataBindingUtil.setContentView(this,R.layout.activity_signup);
        activitySignupBinding.setLifecycleOwner(this);
        activitySignupBinding.setViewModel(signUpViewModel);
    }


    public void set_date(View v) {
        TextInputLayout birth = findViewById(R.id.birthday);
        Calendar calendar = Calendar.getInstance();

        final int m_day = calendar.get(Calendar.DAY_OF_MONTH);
        final int m_month = calendar.get(Calendar.MONTH);
        final int m_year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, (view, year, month, dayOfMonth) -> {
            calendar.set(year,month,dayOfMonth);
            String str = calendar.get(Calendar.DAY_OF_MONTH)+"/"+Integer.valueOf(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
            Log.d("Date:",(str));
            birth.getEditText().setText(str); //OK
            signUpViewModel.Birthdate.setValue(str);
        }, m_year, m_month, m_day);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, -12);
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -80);

        datePickerDialog.getDatePicker().setMinDate(min.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(max.getTimeInMillis());
        //
        datePickerDialog.getDatePicker().getTouchables().get(0).performClick();
        datePickerDialog.show();
    }


    private boolean isUsernameValid = false;
    private boolean isPasswordValid = false;
    private boolean isSamePassword = false;
    private boolean isMailValid = false;
    private boolean isTermsValid = false;



    /**
     * S'encarrega de fer totes les comprovacions necessaries per a que el registre es ralitzi de forma satisfactòria
     */
    protected void checking() {
        //comprovació username
        TextInputLayout username = findViewById(R.id.name_textinputlayout);
        username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                isUsernameValid = AccountValidator.check_usernameValid(s.toString());
                updateForm(isUsernameValid, username, getString(R.string.error_username_no_vàlid));
            }
        });

        //comprovació contra
        TextInputLayout contra = findViewById(R.id.contra_textinputlayout);
        contra.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                isPasswordValid = AccountValidator.check_passwordValid(s.toString());
                updateForm(isPasswordValid, contra, getString(R.string.error_contra_no_vàlida));
            }
        });


        //comprovació confirmar contra
        TextInputLayout confi_contra = findViewById(R.id.contra_con);
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
                    confi_contra.setErrorEnabled(false);
                    isSamePassword = true;
                }
                tots_camps_valids();

            }
        });

        //comprovació MAIL
        TextInputLayout email = findViewById(R.id.username);
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
        b.setEnabled(isPasswordValid && isSamePassword && isMailValid && isTermsValid); //TODO mirar si la data es vàlida
    }



    @SuppressLint("ResourceType")
    public void missatge_registrat(){
        signUpViewModel.getSignUpResponse().observe(this, aBoolean -> {

            Log.d("SignUpActivity", "El valor de aBoolean és: " + aBoolean);
            if(aBoolean){
                finish();
                showInfoUser(getCurrentFocus(), getText(R.string.signup_ok).toString());

            }
            else {
                showInfoUser(getCurrentFocus(), getText(R.string.signup_error).toString());
            }
        });
    }


    //TODO JAVADOC
    private void updateForm(boolean isValid, TextInputLayout textInput, String error_msg) {
        if (!isValid) {
            textInput.setError(error_msg);
        } else {
            textInput.setErrorEnabled(false);
        }
        tots_camps_valids();
    }




}