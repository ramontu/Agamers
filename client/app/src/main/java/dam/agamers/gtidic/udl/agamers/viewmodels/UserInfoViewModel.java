package dam.agamers.gtidic.udl.agamers.viewmodels;

import androidx.lifecycle.MutableLiveData;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class UserInfoViewModel {
    AccountRepo accountRepo = new AccountRepo();
    public MutableLiveData<String> username = new MutableLiveData<>();


    public void close_session(){
        accountRepo.deleteToken();
        PreferencesProvider.providePreferences().edit().remove("token").commit();
    }

    public void delete_account(){
        accountRepo.delete_account();
        PreferencesProvider.providePreferences().edit().remove("token").commit();
    }
    public MutableLiveData<Integer> getmResponseDeleteAccount(){
        return accountRepo.getmResponseDeleteAccount();
    }
}
