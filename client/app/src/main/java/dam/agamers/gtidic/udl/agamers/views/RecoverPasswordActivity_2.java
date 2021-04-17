package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class RecoverPasswordActivity_2 extends CommonActivity {

    AccountRepo accountRepo;
    View view;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password_2);
        getSupportActionBar().hide();
        accountRepo = new AccountRepo();
        show_response();

    }

    public void recover2(View view){
        this.view = view;
        TextInputLayout mail = findViewById(R.id.recover_2_email_textinputlayout);
        TextInputLayout new_pass = findViewById(R.id.recover_2_new_pass_textinputlayout);
        TextInputLayout code = findViewById(R.id.recover_2_code_textinputlayout);
        account = new Account();
        account.setEmail(mail.getEditText().getText().toString());
        account.setPassword(new_pass.getEditText().getText().toString());
        account.setRecovery_code(code.getEditText().getText().toString());
        Log.d("recover2", account.getEmail()+ account.getPassword() + account.getRecovery_code()); //per alguna rao en comptes de code envia ramon
        accountRepo.recover2_newpass(account); //fins au tot correcte


        //TODO cercle de carrega

    }

    public void show_response(){
        accountRepo.getmRecover2Ok().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //TODO eliminar cercle carregant
                Snackbar snackbar;
                if (accountRepo.getmRecover2Ok().getValue()){
                    snackbar = Snackbar.make(view,R.string.recover_pass_2_ok,10000);
                    snackbar.show();
                    goTo(LogInActivity.class);
                }
                else {
                    snackbar = Snackbar.make(view, R.string.recover_pass_2_error, 10000);
                    snackbar.show();
                }
            }
        });
    }


}