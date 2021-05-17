package dam.agamers.gtidic.udl.agamers.services.jocs;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import dam.agamers.gtidic.udl.agamers.services.account.AccountService;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;


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
    public Call<ResponseBody> update_jocs(@Body Jocs jocs, @Path("id") Integer id) {
        return retrofit.create(JocsService.class).update_jocs(jocs, id);
    }

    //TODO FUNCIONA?
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

    @Override
    public Call<ResponseBody> uploadImage(MultipartBody.Part image) {
        return retrofit.create(JocsService.class).uploadImage(image);
    }
}
