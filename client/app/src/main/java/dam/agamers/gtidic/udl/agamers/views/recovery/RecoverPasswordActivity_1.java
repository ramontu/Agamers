package dam.agamers.gtidic.udl.agamers.views.recovery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.LoandingFragment;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.validators.AccountValidator;

public class RecoverPasswordActivity_1 extends CommonActivity {

    AccountRepo accountRepo;
    View view;
    TextInputLayout _email;
    Button button;
    LoandingFragment loandingFragment = new LoandingFragment(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_1);
        getSupportActionBar().hide();
        accountRepo = new AccountRepo();
        _email = findViewById(R.id.recover_1_email_textinputlayout);
        button = findViewById(R.id.recover_1_button);
        button.setEnabled(false);
        watcher();
    }


    public void recover1(View view) {

        this.view = view;
        loandingFragment.startLoadingDialog();

        TextInputLayout til = findViewById(R.id.recover_1_email_textinputlayout);
        Account account = new Account();
        account.setEmail(Objects.requireNonNull(til.getEditText()).getText().toString());
        accountRepo.recover1_pass(account);


    }

    private void watcher(){
        accountRepo.getmRecover1Ok().observe(this, aBoolean -> {
            loandingFragment.dismisDialog();
            Toast toast;
            if (accountRepo.getmRecover1Ok().getValue()){
                toast = Toast.makeText(getBaseContext(),R.string.recover_pass_1_ok,Toast.LENGTH_LONG);
                toast.show();
                goTo(RecoverPasswordActivity_2.class);
            }
            else {
                toast = Toast.makeText(getBaseContext(),R.string.recover_pass_1_error,Toast.LENGTH_LONG);
                toast.show();
            }
        });

        _email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean a = AccountValidator.check_mailValid(s.toString());
                if (a){
                    _email.setErrorEnabled(false);
                    button.setEnabled(true);
                }
                else {
                    _email.setErrorEnabled(true);
                    _email.setError(getString(R.string.error_mail_no_vàlid));
                }
            }
        });
    }
}