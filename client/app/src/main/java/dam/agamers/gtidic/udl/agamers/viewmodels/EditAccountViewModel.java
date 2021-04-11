package dam.agamers.gtidic.udl.agamers.viewmodels;

import androidx.lifecycle.MutableLiveData;

import java.util.Date;
import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class EditAccountViewModel {

    AccountRepo accountRepo;


    public MutableLiveData<Account> accountMutableLiveData = new MutableLiveData<>();

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

    public void setInfo(){
        //descarrega la informació
        accountRepo.download_user_info();
        accountMutableLiveData = accountRepo.getmAccountInfo();


        username.setValue(prov.getUsername());
        username.setValue(Objects.requireNonNull(accountRepo.getmAccountInfo().getValue()).getUsername().toString());
        short_description.setValue(accountRepo.getmAccountInfo().getValue().getShort_description());
    }

}
