package dam.agamers.gtidic.udl.agamers.services;

import dam.agamers.gtidic.udl.agamers.models.Account;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AccountService {

    @GET("/account/profile")
    Call<ResponseBody> download_user_info(); //FUNCIONA

    @POST("/account/profile/update_profile_image")
    Call<ResponseBody> update_porfile_image(); //TODO COMPROVAR

    @POST("/account/create_token")
    Call<ResponseBody> createUserToken(); //FUNCIONA

    @DELETE("/account/delete_token")
    Call<ResponseBody> deleteUserToken(); //TODO COMPROVAR

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









}
