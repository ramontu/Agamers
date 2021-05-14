package dam.agamers.gtidic.udl.agamers.services.jocs;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;


public class JocsServiceImpl implements JocsService {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();


    @Override
    public Call<Jocs> download_jocs_info() {
        return retrofit.create(JocsService.class).download_jocs_info();
    }

    @Override
    public Call<ResponseBody> update_jocs_image() {
        return retrofit.create(JocsService.class).update_jocs_image();
    }

    @Override
    public Call<ResponseBody> delete_jocs() {
        return retrofit.create(JocsService.class).delete_jocs();
    }

    @Override
    public Call<ResponseBody> update_jocs(Jocs jocs) {
        return null;
    }

    @Override
    public Call<ResponseBody> create_jocs(Jocs jocs) {
        return null;
    }

    @Override
    public Call<ResponseBody> update_jocs() {
        return retrofit.create(JocsService.class).update_jocs();
    }

    @Override
    public Call<ResponseBody> create_jocs() {
        return retrofit.create(JocsService.class).create_jocs();
    }
}
