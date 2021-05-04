package dam.agamers.gtidic.udl.agamers.views.recovery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;

public class RecoverPasswordActivity_2 extends CommonActivity {

    AccountRepo accountRepo;
    View view;
    private Account account;
    ProgressBar progressBar;
    TextInputLayout _mail;
    TextInputLayout _recoverycode;
    TextInputLayout _newpass;
    Button button;
    Boolean mailValid = false;
    Boolean codeValid = false;
    Boolean newpassValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_2);
        getSupportActionBar().hide();
        accountRepo = new AccountRepo();
        show_response();
        progressBar = findViewById(R.id.recover_2_progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        _mail = findViewById(R.id.recover_2_email_textinputlayout);
        _recoverycode = findViewById(R.id.recover_2_code_textinputlayout);
        _newpass = findViewById(R.id.recover_2_new_pass_textinputlayout);
        button = findViewById(R.id.recovery_2_button);
        button.setEnabled(false);
        validator();
    }

    public void recover2(View view){
        this.view = view;

        progressBar.setIndeterminate(true);
        progressBar.bringToFront();
        progressBar.setVisibility(View.VISIBLE);

        account = new Account();
        account.setEmail(_mail.getEditText().getText().toString());
        account.setPassword(_newpass.getEditText().getText().toString());
        account.setRecovery_code(_recoverycode.getEditText().getText().toString());
        Log.d("recover2", account.getEmail()+ account.getPassword() + account.getRecovery_code());
        accountRepo.recover2_newpass(account);

    }

    public void show_response(){
        accountRepo.getmRecover2Ok().observe(this, aBoolean -> {
            progressBar.setVisibility(View.INVISIBLE);
            Toast toast;
            if (accountRepo.getmRecover2Ok().getValue()){
                toast = Toast.makeText(getBaseContext(),R.string.recover_pass_2_ok,Toast.LENGTH_LONG);
                toast.show();
                goTo(LogInActivity.class);
                finishAffinity();
            }
            else {
                toast = Toast.makeText(getBaseContext(), R.string.recover_pass_2_error, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    public void validator(){
       _mail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mailValid = AccountValidator.check_mailValid(s.toString());
                updateForm(mailValid,_mail,getString(R.string.error_mail_no_vàlid));
            }
        });

        _recoverycode.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                codeValid = AccountValidator.check_recoverycodeValid(s.toString());
                updateForm(codeValid, _recoverycode, getString(R.string.error_recoverycode_no_vàlid));
            }
        });

        _newpass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                newpassValid = AccountValidator.check_passwordValid(s.toString());
                updateForm(newpassValid,_newpass,getString(R.string.error_contra_no_vàlida));
            }
        });
    }

    public void tots_camps_valids(){
        if (mailValid && codeValid && newpassValid){
            button.setEnabled(true);
        }
    }

    //TODO JAVADOC
    private void updateForm(boolean isValid, TextInputLayout textInput, String error_msg) {
        if (!isValid) {
            textInput.setErrorEnabled(true);
            textInput.setError(error_msg);
        } else {
            textInput.setErrorEnabled(false);
        }
        tots_camps_valids();
    }


}