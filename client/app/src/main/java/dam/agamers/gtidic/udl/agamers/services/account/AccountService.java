package dam.agamers.gtidic.udl.agamers.services.account;

import com.google.gson.Gson;

import dam.agamers.gtidic.udl.agamers.models.Account;

import dam.agamers.gtidic.udl.agamers.models.Token;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface AccountService {

    @GET("/account/profile")
    Call<Account> download_user_info();

    @POST("/account/profile/update_profile_image")
    Call<ResponseBody> update_porfile_image(); //TODO COMPROVAR

    @POST("/account/create_token")
    Call<ResponseBody> createUserToken(); //FUNCIONA

    @HTTP(method = "DELETE", path = "/account/delete_token", hasBody = true)
    Call<ResponseBody> deleteUserToken(@Body Gson token); //TODO FALTA QUE PASSI PEL BODY TOKEN:XXXXXXXXXXX

    @DELETE("/account/delete_account")
    Call<ResponseBody> delete_account(); //TODO SEMBLA QUE FUNCIONA

    @POST("/account/update_account")
    Call<ResponseBody> update_account(@Body Account account); //TODO COMPROVAR

    @POST("/users/register")
    Call<ResponseBody> register(@Body Account account);

    @POST("/users/show/{username}")
    Call<ResponseBody> getPorfile(); //TODO COMPROVAR

    @Multipart
    @POST("/account/profile/update_profile_image")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);

    @POST("/account/recovery")
    Call<ResponseBody> recoverPassword(@Body Account account);

    @POST("/account/password_update")
    Call<ResponseBody> setPassword(@Body Account account);







}
