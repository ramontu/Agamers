package dam.agamers.gtidic.udl.agamers.views.recovery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.LoandingFragment;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.databinding.ActivityIniciDeSessioBinding;
import dam.agamers.gtidic.udl.agamers.databinding.ActivityRecoverPassword2Binding;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;

public class RecoverPasswordActivity_2 extends CommonActivity {

    AccountRepo accountRepo;
    TextInputLayout _mail;
    TextInputLayout _recoverycode;
    TextInputLayout _newpass;
    TextInputLayout _newpasssame;
    Button button;
    Boolean mailValid = false;
    Boolean codeValid = false;
    Boolean newpassValid = false;
    Boolean newpass_same = false;
    LoandingFragment loandingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_2);
        getSupportActionBar().hide();


        _mail = findViewById(R.id.recover_2_email_textinputlayout);
        _recoverycode = findViewById(R.id.recover_2_code_textinputlayout);
        _newpass = findViewById(R.id.recover_2_new_pass_textinputlayout);
        _newpasssame = findViewById(R.id.recover_2_new_pass_rev_textinputlayout);

        accountRepo = new AccountRepo();
        show_response();
        RecoverPasswordViewModel_2 recoverPasswordViewModel_2 = new RecoverPasswordViewModel_2();
         ActivityRecoverPassword2Binding recoverPassword2Binding =
                DataBindingUtil.setContentView(this, R.layout.activity_recover_password_2);
        recoverPassword2Binding.setLifecycleOwner(this);
        recoverPassword2Binding.setViewModel(recoverPasswordViewModel_2);
        observe_result(recoverPasswordViewModel_2);
        loandingFragment = recoverPasswordViewModel_2.loandingFragment = new LoandingFragment(this);
    }

    public void observe_result(RecoverPasswordViewModel_2 recoverPasswordViewModel_2){
        recoverPasswordViewModel_2.email.observe(this,
                s -> {
                    mailValid = AccountValidator.check_mailValid(s);
                    updateForm(mailValid,_mail,
                            getString(R.string.error_mail_no_vàlid));
                });

        recoverPasswordViewModel_2.recovery_code.observe(this,
                s -> {
                    codeValid = AccountValidator.check_recoverycodeValid(s);
                    updateForm(codeValid, _recoverycode,
                            getString(R.string.error_recoverycode_no_vàlid));
                });

        recoverPasswordViewModel_2.new_pass.observe(this, s -> {
            newpassValid = AccountValidator.check_passwordValid(s);
            updateForm(newpassValid,_newpass,
                    getString(R.string.error_contra_no_vàlida));
        });

        recoverPasswordViewModel_2.new_pass_2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                newpass_same = (recoverPasswordViewModel_2.new_pass == recoverPasswordViewModel_2.new_pass_2);
                updateForm(newpass_same,_newpasssame, getString(R.string.error_confirmar_contra));
            }
        });
    }

    public void show_response(){
        accountRepo.getmRecover2Ok().observe(this, aBoolean -> {
            loandingFragment.dismisDialog();
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

    public void tots_camps_valids(){
        if (mailValid && codeValid && newpassValid && newpass_same){
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