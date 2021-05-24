package dam.agamers.gtidic.udl.agamers.services.plataformes;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Categories;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.models.Plataformes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlataformesService {

    @GET("/platforms")
    Call<List<Plataformes>> download_plataformes_info();

    @POST("/platform/create")
    Call<ResponseBody> create_platform(@Body Plataformes plataformes);

    @DELETE("/platform/delete/{id}")
    Call<ResponseBody> delete_platform(@Body Plataformes plataformes, @Path("id") Integer id);
}
