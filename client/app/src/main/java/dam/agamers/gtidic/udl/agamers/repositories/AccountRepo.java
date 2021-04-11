package dam.agamers.gtidic.udl.agamers.repositories;


import android.content.SharedPreferences;
import android.os.SharedMemory;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;


import androidx.lifecycle.MutableLiveData;


import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import org.json.JSONException;
import org.json.JSONObject;



import java.io.IOException;
import java.sql.Date;


import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.enums.AccountTypeEnum;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.services.AccountService;
import dam.agamers.gtidic.udl.agamers.services.AccountServiceImpl;
import dam.agamers.gtidic.udl.agamers.views.LogInActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountRepo {

    private final String TAG = "AccountRepo";

    private final AccountService accountService;
    private MutableLiveData<String> mResponseRegister;
    private MutableLiveData<String> mResponseLogin;
    private MutableLiveData<String> mResponse_download_user_info;
    private MutableLiveData<Account> mAccountInfo;

    Account account = new Account();



    public AccountRepo() {
        this.accountService = new AccountServiceImpl();
        this.mResponseRegister = new MutableLiveData<>();

        this.mResponseLogin = new MutableLiveData<>();
        this.mResponse_download_user_info = new MutableLiveData<>();
        this.mAccountInfo = new MutableLiveData<>();

    }

    public void registerAccount(Account account){

        accountService.register(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  response.errorBody());

                if (return_code == 200){
                    mResponseRegister.setValue("El registre s'ha fet correctament!!!!");
                }else{

                    Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  response.errorBody());
                    mResponseRegister.setValue(TAG+"ERROR DESCONEGUT");
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

        accountService.createUserToken().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int code = response.code();
                Log.d(TAG,  "create_user_token() -> Backend sent:  " + code);
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

    public void download_user_info(){

        accountService.download_user_info().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                Log.d(TAG,  "download_user_info() -> Backend sent:  " + code);
                if (code == 200 ){

                    String jsonstring = "";
                    try {
                        jsonstring = response.body().string().toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    JSONObject jo;
                    try {
                        jo = new JSONObject(jsonstring);
                        //account.setCreated_at((Date) jo.get("created_at"));
                        account.setUsername((String) jo.get("username"));
                        //account.setAccount_type((AccountTypeEnum) jo.get("account_type")); //TODO pasar a enum
                        account.setShort_description((String) jo.get("short_description"));
                        account.setLong_description((String) jo.get("long_description"));
                        //account.setPassword((String) jo.get("password")); TODO encara no el baixa
                        account.setEmail("email");
                        account.setName((String) jo.get("name"));
                        account.setSurname((String) jo.get("surname"));
                        account.setBirthday((String) jo.get("birthday"));
                        //account.setGenere((GenereEnum) jo.get("genere"));
                        //account.setPhoto(); //TODO
                        mAccountInfo.setValue(account);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "download_user_info() onFailure() -> ha rebut el missatge:  " + error_msg);

                mResponse_download_user_info.setValue(error_msg);
            }
        });

    }

    public MutableLiveData<Account> getmAccountInfo(){
        return mAccountInfo;
    }
}


