package dam.agamers.gtidic.udl.agamers.services.xats;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;


public class XatsServiceImpl implements XatsService {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();


    @Override
    public Call<List<String>> download_all_xats_ids(Integer id) {
        return retrofit.create(XatsService.class).download_all_xats_ids(id);
    }

    @Override
    public Call<ResponseBody> create_new_xat(String chat_id, Integer user_id) {
        return retrofit.create(XatsService.class).create_new_xat(chat_id, user_id);
    }

    @Override
    public Call<ResponseBody> save_new_chat(Integer id) {
        return retrofit.create(XatsService.class).save_new_chat(id);
    }

    //TODO falta una d'eliminar
}
