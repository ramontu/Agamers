package dam.agamers.gtidic.udl.agamers.services.account;

import com.google.gson.Gson;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Token;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;


public class AccountServiceImpl implements AccountService {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();



    @Override
    public Call<Account> download_user_info() {
        return retrofit.create(AccountService.class).download_user_info();
    }

    @Override
    public Call<ResponseBody> update_porfile_image() {
        return retrofit.create(AccountService.class).update_porfile_image();
    }

    @Override
    public Call<ResponseBody> createUserToken() {
        return retrofit.create(AccountService.class).createUserToken();
    }

    @Override
    public Call<ResponseBody> deleteUserToken(Gson token) {
        return retrofit.create(AccountService.class).deleteUserToken(token);
    }

    @Override
    public Call<ResponseBody> delete_account() {
        return retrofit.create(AccountService.class).delete_account();
    }

    @Override
    public Call<ResponseBody> update_account(Account account) {
        return retrofit.create(AccountService.class).update_account(account);
    }

    @Override
    public Call<ResponseBody> register(Account account) {
        return retrofit.create(AccountService.class).register(account);
    }

    //TODO MIRAR COM PASSAR EL USERNAME
    @Override
    public Call<ResponseBody> getPorfile() {
        return retrofit.create(AccountService.class).getPorfile();
    }

    @Override
    public Call<ResponseBody> uploadImage(MultipartBody.Part image) {
        return retrofit.create(AccountService.class).uploadImage(image);
    }

    @Override
    public Call<ResponseBody> recoverPassword(Account account) {
        return retrofit.create(AccountService.class).recoverPassword(account);
    }

    @Override
    public Call<ResponseBody> setPassword(Account account) {
        return retrofit.create(AccountService.class).setPassword(account);
    }

    public Call<List<Account>> getInfoMatch() { return retrofit.create(AccountService.class).getInfoMatch(); }

}