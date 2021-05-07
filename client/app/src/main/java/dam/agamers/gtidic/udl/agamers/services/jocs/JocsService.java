package dam.agamers.gtidic.udl.agamers.services.jocs;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JocsService {

    @GET("/jocs/nom")
    Call<Jocs> download_jocs_info();

    @POST("/jocs/nom/update_image")
    Call<ResponseBody> update_jocs_image();

    @DELETE("/jocs/delete_jocs")
    Call<ResponseBody> delete_jocs();

    @POST("/jocs/update_jocs")
    Call<ResponseBody> update_jocs(Jocs jocs);

    @POST("/jocs/create_jocs")
    Call<ResponseBody> create_jocs(Jocs jocs);


    Call<ResponseBody> create_jocs();

    Call<ResponseBody> update_jocs();
}
