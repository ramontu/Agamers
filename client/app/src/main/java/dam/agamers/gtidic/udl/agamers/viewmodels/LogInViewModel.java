package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.nio.charset.StandardCharsets;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class LogInViewModel {

    private static final String TAG = "LogInVM";
    private final AccountRepo accountRepo;
    public MutableLiveData<String> username;
    public MutableLiveData<String> password;

    public LogInViewModel() {
        accountRepo = new AccountRepo();
        username = new MutableLiveData<>();
        password = new MutableLiveData<>();

    }

    public void onLogin() {
        // @TODO: Revisar que username i password siguin valids
        Log.d(TAG,username.getValue() + ":" + password.getValue());
        String auth_token = username.getValue() + ":" + password.getValue();
        byte[] data = auth_token.getBytes(StandardCharsets.UTF_8);
        auth_token = Base64.encodeToString(data, Base64.DEFAULT);
        auth_token = ("Autenthication " + auth_token).trim();
        System.err.println(auth_token);

        PreferencesProvider.providePreferences().edit().putString("token", auth_token).commit();
        this.accountRepo.createUserToken();

    }


    public MutableLiveData<String> getResponseLogin() {
        return accountRepo.getmResponseLogin();
    }
}


