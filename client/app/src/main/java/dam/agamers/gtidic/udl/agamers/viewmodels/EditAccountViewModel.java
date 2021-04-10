package dam.agamers.gtidic.udl.agamers.viewmodels;

import androidx.lifecycle.MutableLiveData;

import java.util.Date;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class EditAccountViewModel {

    AccountRepo accountRepo;


    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> account_type = new MutableLiveData<>(); //TODO passar a a Enum
    public MutableLiveData<String> short_description = new MutableLiveData<>();
    public MutableLiveData<String> long_description = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> surname = new MutableLiveData<>();
    public MutableLiveData<Date> birthday = new MutableLiveData<>();
    public MutableLiveData<String> genere = new MutableLiveData<>(); //TODO passar a enum
    public MutableLiveData<String> photo = new MutableLiveData<>(); //TODO COMPLETAR AMB LA PHOTO


    public EditAccountViewModel(){
        accountRepo = new AccountRepo();

    }

    public MutableLiveData<Account> getAccountInformation(){
        accountRepo.download_user_info();
        return accountRepo.getmAccountInfo();
    }

}
