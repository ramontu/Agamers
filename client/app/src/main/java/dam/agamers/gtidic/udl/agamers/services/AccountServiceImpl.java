package dam.agamers.gtidic.udl.agamers.services;

import android.accounts.Account;

import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class AccountServiceImpl implements AccountServiceI {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<ResponseBody> registre(Account account) {
        return retrofit.create(AccountServiceI.class).registre(account);
    }
}
