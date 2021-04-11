package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.viewmodels.EditAccountViewModel;

public class EditAccountActivity extends CommonActivity {

    private EditAccountViewModel editAccountViewModel;
    private Account account;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
       getSupportActionBar().hide();
        editAccountViewModel = new EditAccountViewModel();
        account = new Account();

        setInformation();
    }




    private void setInformation(){
        editAccountViewModel.setInfo();
    }

}