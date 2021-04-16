package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Base64;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.nio.charset.StandardCharsets;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.views.LogInActivity;

public class LogInViewModel {

    AccountRepo accountRepo;

    public LogInViewModel() {
        accountRepo = new AccountRepo();
    }

    /**
     * @param email variable per guardar l'email que introdueixi l'usuari
     * @param password variable per guardar la contrasenya que introdueixi l'usuari
     */
    public void login(String email, String password) {

        String auth_token = email + ":" + password;
        byte[] data = auth_token.getBytes(StandardCharsets.UTF_8);
        auth_token = Base64.encodeToString(data, Base64.DEFAULT);
        auth_token = ("Autenthication " + auth_token).trim();
        System.err.println(auth_token);

        PreferencesProvider.providePreferences().edit().putString("token", auth_token).apply();
        this.accountRepo.createUserToken();
    }

    /**
     * @return retorna la resposta del login
     */
    public MutableLiveData<String> getResponseLogin() {
        return accountRepo.getmResponseLogin();
    }
}


