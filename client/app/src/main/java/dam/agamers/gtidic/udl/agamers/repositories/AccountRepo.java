package dam.agamers.gtidic.udl.agamers.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import java.io.IOException;


import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.services.AccountService;
import dam.agamers.gtidic.udl.agamers.services.AccountServiceImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepo {

    private final String TAG = "AccountRepo";

    private final AccountService accountService;
    private final MutableLiveData<String> mResponseRegister;

    private AccountService account_service;
    private MutableLiveData<String> mResponseLogin;

    public AccountRepo() {
        this.accountService = new AccountServiceImpl();
        this.mResponseRegister = new MutableLiveData<>();

        this.account_service = new AccountServiceImpl();
        this.mResponseLogin = new MutableLiveData<>();
    }

    public void registerAccount(Account account){

        accountService.register(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  return_code);

                if (return_code == 200){
                    mResponseRegister.setValue("El registre s'ha fet correctament!!!!");
                }else{
                    String error_msg = "Error: " + response.errorBody();
                    mResponseRegister.setValue(error_msg);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                mResponseRegister.setValue(error_msg);
            }
        });

    }

    public void createUserToken() {

        account_service.createUserToken().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int code = response.code();
                Log.d(TAG,  "createTokenUser() -> Backend sent:  " + code);

                if (code == 200 ){
                    try {
                        String authToken = response.body().string().split(":")[1];
                        authToken=authToken.substring(2,authToken.length()-2);
                        Log.d(TAG,  "createTokenUser() -> ha rebut el token:  " + authToken);
                        mResponseLogin.setValue(authToken);
                        PreferencesProvider.providePreferences().edit().
                                putString("token", authToken).apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "createTokenUser() -> ha rebut l'error:  " + error_msg);
                        PreferencesProvider.providePreferences().edit().remove("token").apply();
                        mResponseLogin.setValue(error_msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "createTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
                PreferencesProvider.providePreferences().edit().remove("token").apply();
                mResponseLogin.setValue(error_msg);
            }

        });
    }

    public MutableLiveData<String> getmResponseLogin() {
        return mResponseLogin;
    }
}


