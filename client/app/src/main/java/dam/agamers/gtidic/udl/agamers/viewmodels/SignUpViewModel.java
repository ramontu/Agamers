package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class SignUpViewModel extends ViewModel {
    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData<String> Username = new MutableLiveData<>();
    //public MutableLiveData<String> Name = new MutableLiveData<>();
    //public MutableLiveData<String> Surname = new MutableLiveData<>();
    public MutableLiveData<String> Date = new MutableLiveData<>();
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    public SignUpViewModel() {
        this.accountRepo = new AccountRepo();
    }

    public void onRegister() {
        System.err.println("onRegister: He entrar a singupmodel");
        Account account = new Account();
        //account.setName(Name.getValue());
        //account.setSurname(Surname.getValue());
        account.setUsername(Username.getValue());
        account.setEmail(Email.getValue());
        account.setPassword(Password.getValue());
        try {
            account.setDate(Date.getValue());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG,account.toString());
        this.accountRepo.registerAccount(account);

    }

}
