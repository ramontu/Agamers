package dam.agamers.gtidic.udl.agamers.services.plataformes;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Plataformes;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class PlataformesServiceImpl implements PlataformesService {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<List<Plataformes>> download_plataformes_info() {
        return retrofit.create(PlataformesService.class).download_plataformes_info();
    }

    @Override
    public Call<ResponseBody> create_platform(Plataformes plataformes) {
        return retrofit.create(PlataformesService.class).create_platform(plataformes);
    }

    @Override
    public Call<ResponseBody> delete_plataform(@Body Plataformes plataformes, @Path("id") Integer id) {
        return retrofit.create(PlataformesService.class).delete_plataform(plataformes,id);
    }
}
