package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.services.xats.XatsService;
import dam.agamers.gtidic.udl.agamers.services.xats.XatsServiceImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRepo {


    private final String TAG = "ChatRepo";

    private final XatsService xatsService;

    private MutableLiveData<List<String>> all_users_xats;
    private MutableLiveData<Boolean> mAddChatOK;

    public ChatRepo() {
        this.xatsService = new XatsServiceImpl();
        this.all_users_xats = new MutableLiveData<>();
        this.mAddChatOK = new MutableLiveData<>();
    }

    /**
     * Descarrega tots els id dels xats als que participa el user per a buscar-los a la firebase
     * @param user_id Id del usuari del que ho volem
     */
    private void get_all_user_xats(Integer user_id){
        xatsService.download_all_xats_ids(user_id).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                all_users_xats.setValue(response.body());
                Log.d(TAG, "Download all xats onResponse():"+ response.isSuccessful());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                all_users_xats.setValue(null);
                Log.d(TAG, "Download all xats onFailure():"+ t.getMessage());
                t.printStackTrace();
            }
        });
    }

    /**
     * Descarrega els id dels xats que l'usuari té
     * @param id Integer: Id de l'usuari del que es vol la informació
     * @return MutableLiveData\<List<String>>: Conté una llista amb els id dels xats per a buscar-los a la firebase, si es null implica onFailure
     */
    public MutableLiveData<List<String>> getAll_users_xats(Integer id) {
        get_all_user_xats(id);
        return all_users_xats;
    }


    private void add_new_chat(String chat_id, Integer user_id){
        xatsService.create_new_xat(chat_id, user_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mAddChatOK.setValue(response.isSuccessful());
                Log.d(TAG, "Add_new_chat onResponse():"+ response.isSuccessful());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mAddChatOK.setValue(false);
                Log.d(TAG, "Add_new_chat onFailure:"+ t.getMessage());
                t.printStackTrace();
            }
        });
    }


    /**
     * Afegeix el id del xat a la BBDD nostra
     * @param chat_id String: Id de xat que li ha donat Firebase al crear el nou xat
     * @param user_id Integer: Id de l'usuari
     * @return Boolean: Resposta de
     */
    public MutableLiveData<Boolean> getmAddChatOK(String chat_id, Integer user_id) {
        add_new_chat(chat_id, user_id);
        return mAddChatOK;
    }
}
