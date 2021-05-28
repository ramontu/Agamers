package dam.agamers.gtidic.udl.agamers.services.jocs;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Event;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface JocsService {

    @GET("/jocs/nom")
    Call<Jocs> download_jocs_info();

    @POST("/jocs/nom/update_image")
    Call<ResponseBody> update_jocs_image();

    @DELETE("/jocs/delete_jocs")
    Call<ResponseBody> delete_jocs();

    @POST("/jocs/update_jocs/{id}")
    Call<ResponseBody> update_jocs(@Body Jocs jocs, @Path("id") Integer id);

    @POST("/jocs/create_jocs")
    Call<ResponseBody> create_jocs(@Body Jocs jocs);

    @Multipart
    @POST("/jocs/update_profile_image")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);

    @GET("jocs")
    Call<List<Jocs>> getJocs();

    @GET("/jocs/{id}")
    Call<Jocs> infojocs(@Path("id") Integer id);
}
