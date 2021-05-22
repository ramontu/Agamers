package dam.agamers.gtidic.udl.agamers.services.xats;

import java.util.List;
import java.util.zip.Inflater;

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

public interface XatsService {

    @GET("xats/all/{id}")
    Call<List<String>> download_all_xats_ids(@Path("id")Integer id);

    @POST("xats/add_user_to_chat/{chat_id}/{user_id}")
    Call<ResponseBody> create_new_xat(@Path("chat_id") String chat_id, @Path("user_id") Integer user_id);

    @POST("xats/save_new_chat/{id}")
    Call<ResponseBody> save_new_chat(@Path("id") Integer id);

    
}
