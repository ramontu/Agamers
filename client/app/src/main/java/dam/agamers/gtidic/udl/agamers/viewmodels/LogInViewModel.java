package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Base64;

import androidx.lifecycle.MutableLiveData;

import java.nio.charset.StandardCharsets;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class LogInViewModel {

    AccountRepo accountRepo;

    public LogInViewModel(){
        accountRepo = new AccountRepo();
    }

    public void login(String email, String password){
        String auth_token = email + ":" + password;
        byte[] data = auth_token.getBytes(StandardCharsets.UTF_8);
        auth_token = Base64.encodeToString(data, Base64.DEFAULT);
        //Authoritzation: YWRtaW46REFNQ291cmU=
        auth_token = ("Authoritzation: " + auth_token).trim();
        //System.err.println(auth_token);

        PreferencesProvider.providePreferences().edit().putString("token", auth_token).apply();

        this.accountRepo.createUserToken();
    }

    public MutableLiveData<String> getResponseLogin() {
        return this.accountRepo.getmResponseLogin();
    }
}
