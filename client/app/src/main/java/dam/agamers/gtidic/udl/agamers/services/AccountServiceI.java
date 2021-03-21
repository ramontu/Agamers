package dam.agamers.gtidic.udl.agamers.services;

import android.accounts.Account;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountServiceI {

    @POST("account/create_token")
    Call<ResponseBody> registre(@Body Account account);

}
