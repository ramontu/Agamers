package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class Registre_1ViewModel extends ViewModel {
    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData<String> Username = new MutableLiveData<>();
    public MutableLiveData<String> Name = new MutableLiveData<>();
    public MutableLiveData<String> Surname = new MutableLiveData<>();
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    public Registre_1ViewModel() {
        this.accountRepo = new AccountRepo();
    }

    public void onRegister(){
        System.err.println("onRegister: He entrar a singupmodel");
        Account account = new Account();
        account.setName(Name.getValue());
        account.setSurname(Surname.getValue());
        account.setEmail(Email.getValue());
        account.setPassword(Password.getValue());

        //default values per a que no es queixi
        account.setUsername("prova_client");
        Log.d(TAG,account.toString());
        this.accountRepo.registerAccount(account);
        System.err.println("onRegister: he intentat registrar");

    }

}
