package dam.agamers.gtidic.udl.agamers.views.compte;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.repositories.ProfileRepo;

public class CompteViewModel extends ViewModel {

    AccountRepo accountRepo = new AccountRepo();



    public void delete_account(){
        accountRepo.delete_account();
        PreferencesProvider.providePreferences().edit().remove("token").commit();
    }
    public MutableLiveData<Integer> getmResponseDeleteAccount(){
        return accountRepo.getmResponseDeleteAccount();
    }

    public MutableLiveData<Account> agafarProfile(){
        return accountRepo.getmAccountInfo();
    }

}
