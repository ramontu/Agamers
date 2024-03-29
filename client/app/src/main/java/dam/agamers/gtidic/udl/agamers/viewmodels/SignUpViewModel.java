package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.Date;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.views.LoandingFragment;

public class SignUpViewModel extends ViewModel {
    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData<String> Username = new MutableLiveData<>();
    public MutableLiveData<String> Birthdate = new MutableLiveData<String>();
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<Boolean> mResponse = new MutableLiveData<>();

    public LoandingFragment loandingFragment;

    public SignUpViewModel() {
        this.accountRepo = new AccountRepo();
    }

    public void onRegister() {
        loandingFragment.startLoadingDialog();
        System.err.println("onRegister: He entrar a singupmodel");
        Account account = new Account();
        account.setUsername(Username.getValue());
        account.setEmail(Email.getValue());
        account.setPassword(Password.getValue());
        account.setDate(Birthdate.getValue());
        Log.d(TAG,account.toString());
        this.accountRepo.registerAccount(account);
    }

    public MutableLiveData<Boolean> getSignUpResponse(){
        return accountRepo.getmSignUpOk();
    }
}
