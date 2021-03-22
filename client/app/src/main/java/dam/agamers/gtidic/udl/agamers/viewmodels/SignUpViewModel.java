package dam.agamers.gtidic.udl.agamers.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class SignUpViewModel extends ViewModel {
    private AccountRepo accountRepo;

    public MutableLiveData<String> Username = new MutableLiveData<>();
    public MutableLiveData<String> Name = new MutableLiveData<>();
    public MutableLiveData<String> Surname = new MutableLiveData<>();
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    public void onRegister(){
        Account account = new Account();
        account.setName(Name.getValue());
        account.setSurname(Surname.getValue());
        account.setEmail(Email.getValue());
        account.setPassword(Password.getValue());

        //default values per a que no es queixi
        account.setUsername("prova_client");

        this.accountRepo.registerAccount(account);

    }

}
