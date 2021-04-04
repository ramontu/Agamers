package dam.agamers.gtidic.udl.agamers.services;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;


public class AccountServiceImpl implements AccountService {

    private Retrofit refrotit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<ResponseBody> register(Account account) {
        return refrotit.create(AccountService.class).register(account);
    }

    @Override
    public Call<ResponseBody> createUserToken() {
        return refrotit.create(AccountService.class).createUserToken();
    }


}
