package dam.agamers.gtidic.udl.agamers.services;

import android.text.PrecomputedText;

import java.io.File;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Header;


public class AccountServiceImpl implements AccountService {

    private Retrofit refrotit = RetrofitClientInstance.getRetrofitInstance();



    @Override
    public Call<ResponseBody> download_user_info() {
        return refrotit.create(AccountService.class).download_user_info();
    }

    @Override
    public Call<ResponseBody> update_porfile_image() {
        return refrotit.create(AccountService.class).update_porfile_image();
    }

    @Override
    public Call<ResponseBody> createUserToken() {
        return refrotit.create(AccountService.class).createUserToken();
    }

    @Override
    public Call<ResponseBody> deleteUserToken() {
        return refrotit.create(AccountService.class).deleteUserToken();
    }

    @Override
    public Call<ResponseBody> delete_account() {
        return refrotit.create(AccountService.class).delete_account();
    }

    @Override
    public Call<ResponseBody> update_account(Account account) {
        return refrotit.create(AccountService.class).update_account(account);
    }

    @Override
    public Call<ResponseBody> register(Account account) {
        return refrotit.create(AccountService.class).register(account);
    }

    //TODO MIRAR COM PASSAR EL USERNAME
    @Override
    public Call<ResponseBody> getPorfile() {
        return refrotit.create(AccountService.class).getPorfile();
    }

    @Override
    public Call<ResponseBody> uploadImage(MultipartBody.Part image) {
        return refrotit.create(AccountService.class).uploadImage(image);
    }




}