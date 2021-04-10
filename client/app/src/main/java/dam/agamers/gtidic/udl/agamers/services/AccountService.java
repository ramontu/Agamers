package dam.agamers.gtidic.udl.agamers.services;

import dam.agamers.gtidic.udl.agamers.models.Account;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountService {

    @GET("/account/profile")
    Call<ResponseBody> download_user_info();

    @POST("/account/profile/update_profile_image")
    Call<ResponseBody> update_porfile_image(); //TODO COMPROVAR

    @POST("/account/create_token")
    Call<ResponseBody> createUserToken();

    @POST("/account/delete_token")
    Call<ResponseBody> deleteUserToken(); //TODO COMPROVAR

    @POST("/account/delete_account")
    Call<ResponseBody> delete_account(); //TODO COMPROVAR

    @POST("/account/update_account")
    Call<ResponseBody> update_account(@Body Account account); //TODO COMPROVAR

    @POST("/users/register")
    Call<ResponseBody> register(@Body Account account);

    @POST("/users/show/{username}")
    Call<ResponseBody> getPorfile(); //TODO COMPROVAR








}
