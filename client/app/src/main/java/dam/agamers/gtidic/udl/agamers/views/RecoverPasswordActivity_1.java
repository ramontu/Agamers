package dam.agamers.gtidic.udl.agamers.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class RecoverPasswordActivity_1 extends CommonActivity {

    AccountRepo accountRepo;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_1);
        getSupportActionBar().hide();
        accountRepo = new AccountRepo();
        watcher();
    }


    public void recover(View view) {
        this.view = view;
        TextInputLayout til = findViewById(R.id.recover_1_email_textinputlayout);
        Account account = new Account();
        account.setEmail(Objects.requireNonNull(til.getEditText()).getText().toString());
        accountRepo.recover_pass(account);

        //TODO possar cercle de carregant





    }

    private void watcher(){
        accountRepo.getmRecoverOk().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //TODO eliminar cercle carregant
                Snackbar snackbar;
                if (accountRepo.getmRecoverOk().getValue()){
                    snackbar = Snackbar.make(view,R.string.recover_pass_1_ok,10000);
                    snackbar.show();
                    goTo(RecoverPasswordActivity_2.class);
                }
                else {
                    snackbar = Snackbar.make(view, R.string.recover_pass_1_error, 10000);
                    snackbar.show();
                }
            }
        });
    }
}