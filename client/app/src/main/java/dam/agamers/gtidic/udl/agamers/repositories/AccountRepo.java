package dam.agamers.gtidic.udl.agamers.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Token;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.services.account.AccountService;
import dam.agamers.gtidic.udl.agamers.services.account.AccountServiceImpl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    private MutableLiveData<Integer> mResponseDeleteAccount;
    private MutableLiveData<String> mResponseUploadImage;
    private MutableLiveData<Boolean> mRecover1Ok;
    private MutableLiveData<Boolean> mRecover2Ok;
    private MutableLiveData<Boolean> mUpdateOk;
    private MutableLiveData<Boolean> mSignUpOk;
    private MutableLiveData<Boolean> mDeleteTokenOk;
    private MutableLiveData<List<Account>> mResponseMatch;
    private MutableLiveData<Account> mDescarregarInfoMatch;



    public AccountRepo() {
        this.accountService = new AccountServiceImpl();
        this.mResponseRegister = new MutableLiveData<>();
        this.mResponseLogin = new MutableLiveData<>();
        this.mResponse_download_user_info = new MutableLiveData<>();
        this.mAccountInfo = new MutableLiveData<>();
        this.mResponseDeleteAccount = new MutableLiveData<>();
        this.mResponseUploadImage = new MutableLiveData<>();
        this.mRecover1Ok = new MutableLiveData<>();
        this.mRecover2Ok = new MutableLiveData<>();
        this.mUpdateOk = new MutableLiveData<>();
        this.mSignUpOk = new MutableLiveData<>();
        this.mDeleteTokenOk = new MutableLiveData<>();
        this.mResponseMatch = new MutableLiveData<>();
        this.mDescarregarInfoMatch = new MutableLiveData<>();
    }

    public void registerAccount(Account account){
        accountService.register(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int return_code = response.code();  //200, 404, 401,...


                if (return_code == 200){
                    mSignUpOk.setValue(true);
                    Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  response.code());
                }else{
                    Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  response.message());
                    mSignUpOk.setValue(false);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "registerAccount on failure");
                t.printStackTrace();
                mSignUpOk.setValue(false);
            }

        });
    }

    public MutableLiveData<Boolean> getmSignUpOk(){
        return mSignUpOk;
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

    private void download_user_info(){

        accountService.download_user_info().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                mAccountInfo.setValue(response.body());
                Log.d(TAG, "DownloadInfo() : "+response.code() +"user:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d(TAG, "DownloadInfo() : Error"+ t.getMessage());
                t.printStackTrace();
            }
        });

    }

    public MutableLiveData<Account> getmAccountInfo(){
        download_user_info();
        return mAccountInfo;
    }


    public void delete_account(){
        Log.d(TAG,"Entrant deleteaccount");
        accountService.delete_account().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"delete account response"+response.code()+response.errorBody());
                int code = response.code();
                if (code == 200){
                    mResponseDeleteAccount.setValue(R.string.delete_account_ok);
                }
                else{
                    mResponseDeleteAccount.setValue(R.string.delete_account_error);
                    Log.d(TAG, "delete account error"+response.code()+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponseDeleteAccount.setValue(R.string.delete_account_error);
                Log.d(TAG, "delete account error"+t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Integer> getmResponseDeleteAccount(){
        return mResponseDeleteAccount;
    }


    public void uploadPhoto(File imageFile){
        RequestBody reqBody = RequestBody.create(imageFile, MediaType.parse("image/*"));
        MultipartBody.Part image = MultipartBody.Part.createFormData("image_file", imageFile.getName(), reqBody);
        accountService.uploadImage(image).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"uploadPhoto() -> ha rebut el codi: " +  return_code);
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,"uploadPhoto() -> ERROR: " +  error_msg);
                mResponseUploadImage.setValue(error_msg);
            }
        });
    }

    public void recover1_pass(Account account){
        Log.d(TAG, "recover1 pass email:" +account.getEmail()); //email es correcte
        accountService.recoverPassword(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Enviat",call.request().body().toString());
                if (response.code() == 200){
                    mRecover1Ok.setValue(true);
                    Log.d(TAG, "recover1_pass sent: "+response.code());
                }
                else {
                    mRecover1Ok.setValue(false);
                    try {
                        Log.d(TAG, "recover1_pass sent: "+response.code() +response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mRecover1Ok.setValue(false);
                Log.d(TAG, "recover_pass onFailure ");
                t.printStackTrace();
            }
        });
    }
    public MutableLiveData<Boolean> getmRecover1Ok(){
        return mRecover1Ok;
    }


    public void recover2_newpass(Account account){
        Log.d(TAG, "Recover2_newpass"+account.getEmail()+" "+account.getPassword()+" "+account.getRecovery_code()); //fins aqui ok
        accountService.setPassword(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200){
                    mRecover2Ok.setValue(true);
                    Log.d(TAG, "recover2_newpass sent: "+response.code());
                }
                else {
                    mRecover2Ok.setValue(false);
                    try {
                        Log.d(TAG, "recover2_newpass sent: "+response.code() +response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mRecover2Ok.setValue(false);
                Log.d(TAG, "recover2_pass onFailure ");
                t.printStackTrace();
            }
        });
    }
    public MutableLiveData<Boolean> getmRecover2Ok(){
        return mRecover2Ok;
    }

    public void updateAccount(Account account){
        Log.d(TAG,"update info");
        accountService.update_account(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    mUpdateOk.setValue(true);
                    Log.d(TAG, "updateinfo code: "+response.code());
                }
                else {
                    mUpdateOk.setValue(false);
                    try {
                        Log.d(TAG, "updateinfo code: "+response.code()+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "updateinfo Onfaliure ");
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Boolean> getmUpdateOk(){
        return mUpdateOk;
    }

    public void deleteToken() {
        Token token = new Token(PreferencesProvider.providePreferences().getString("token",""));
        Gson gson = new Gson();
        gson.toJson(token);
        accountService.deleteUserToken(gson).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {
                    mDeleteTokenOk.setValue(true);
                    Log.d(TAG, "deleteToken OK");
                }
                else {
                    mDeleteTokenOk.setValue(false);
                    Log.d(TAG, "deleteToken WRONG"+response.code()+response.message());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mDeleteTokenOk.setValue(false);
                Log.d(TAG, "deleteToken onFailure"+t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Boolean> getmDeleteTokenOk() {
        return mDeleteTokenOk;
    }


    public void getInfoMatch() {
        this.accountService.getInfoMatch().enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                int code = response.code();

                if (code == 200) {
                    Log.d("AccountRepo", "getInfoMatch() -> ha rebut el codi: " + code);
                    List<Account> match = response.body();
                    mResponseMatch.setValue(match);
                } else {
                    Log.d("AccountRepo", "getInfoMatch() -> ha rebut el codi: " + code);
                }
            }


            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Account>> getmResponseMatch() { return mResponseMatch; }
}


