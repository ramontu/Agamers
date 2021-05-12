package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.services.jocs.JocsService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JocsRepo {

    private final String TAG = "JocsRepo";
    private final JocsService jocsService;

    private MutableLiveData<Jocs> mJocsInfo;
    private MutableLiveData<Boolean> mUpdateOk;
    private MutableLiveData<Integer> mResponseDeleteJocs;
    private MutableLiveData<Boolean> mCreateOk;

    public JocsRepo(JocsService jocsService){
        this.jocsService = jocsService;
        this.mJocsInfo = new MutableLiveData<>();
        this.mUpdateOk = new MutableLiveData<>();
        this.mResponseDeleteJocs = new MutableLiveData<>();
        this.mCreateOk = new MutableLiveData<>();
    }

    private void download_jocs_info(){

        jocsService.download_jocs_info().enqueue(new Callback<Jocs>() {
            @Override
            public void onResponse(Call<Jocs> call, Response<Jocs> response) {
                mJocsInfo.setValue(response.body());
                Log.d(TAG, "DownloadInfo() : "+response.code() +"joc:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<Jocs> call, Throwable t) {
                Log.d(TAG, "DownloadInfo() : Error"+ t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Jocs> getmJocsInfo(){
        download_jocs_info();
        return mJocsInfo;
    }

    public void update_jocs(Jocs jocs){
        Log.d(TAG,"update info");
        jocsService.update_jocs(jocs, jocs.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    mUpdateOk.setValue(true);
                    Log.d(TAG, "updateinfo code: "+response.code());
                }
                else {
                    mUpdateOk.setValue(false);
                    try {
                        Log.d(TAG, "updateinfo code: "+response.code()+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "updateinfo Onfaliure ");
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Boolean> getmUpdateOk(){
        return mUpdateOk;
    }

    public void delete_jocs(){
        Log.d(TAG,"Entrant deletejocs");
        jocsService.delete_jocs().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"delete jocs response"+response.code()+response.errorBody());
                int code = response.code();
                if (code == 200){
                    mResponseDeleteJocs.setValue(R.string.delete_jocs_ok);
                }
                else{
                    mResponseDeleteJocs.setValue(R.string.delete_jocs_error);
                    Log.d(TAG, "delete jocs error"+response.code()+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponseDeleteJocs.setValue(R.string.delete_jocs_error);
                Log.d(TAG, "delete jocs error"+t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Integer> getmResponseDeleteJocs(){
        return mResponseDeleteJocs;
    }

    public void create_jocs(Jocs jocs){
        Log.d(TAG,"update info");
        jocsService.create_jocs(jocs).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    mCreateOk.setValue(true);
                    Log.d(TAG, "updateinfo code: "+response.code());
                }
                else {
                    mCreateOk.setValue(false);
                    try {
                        Log.d(TAG, "updateinfo code: "+response.code()+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "updateinfo Onfaliure ");
                t.printStackTrace();
            }
        });
    }
    public MutableLiveData<Boolean> getmCreateOkOk(){
        return mCreateOk;
    }

}
