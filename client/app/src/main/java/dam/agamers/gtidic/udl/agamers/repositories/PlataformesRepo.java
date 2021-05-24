package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Plataformes;
import dam.agamers.gtidic.udl.agamers.services.plataformes.PlataformesService;
import dam.agamers.gtidic.udl.agamers.services.plataformes.PlataformesServiceImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class PlataformesRepo {

    //TODO: Omplir el oncreate de el multispinner seguint el tutorial: https://github.com/pratikbutani/MultiSelectSpinner
    //TODO: Seguir els passos fets amb les categories :D

    private final String TAG = "PlataformesRepo";
    private final PlataformesService plataformesService;
    private final MutableLiveData<List<Plataformes>> mPlataformesInfo;
    private final MutableLiveData<Boolean> mCreatePlataformesOk;
    private final MutableLiveData<Integer> mResponseDeletePlatform;

    public PlataformesRepo(){
        this.plataformesService = new PlataformesServiceImpl();
        this.mPlataformesInfo = new MutableLiveData<>();
        this.mCreatePlataformesOk = new MutableLiveData<>();
        this.mResponseDeletePlatform = new MutableLiveData<>();
    }

    public void download_plataformes_info(){
        plataformesService.download_plataformes_info().enqueue(new Callback<List<Plataformes>>() {
            @Override
            public void onResponse(Call<List<Plataformes>> call, Response<List <Plataformes>> response) {
                mPlataformesInfo.setValue(response.body());
                Log.d(TAG, "DownloadInfo() : " +response.code() +"plataforma:" +response.body().toString());
            }
            @Override
            public void onFailure(Call<List<Plataformes>> call, Throwable t) {
                Log.d(TAG, "DownloadInfo() : Error" + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    public MutableLiveData<List<Plataformes>> getmPlataformesInfo() {
        return mPlataformesInfo;
    }

    public void create_platform(Plataformes plataformes){
        Log.d(TAG,"update info");
        plataformesService.create_platform(plataformes).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    mCreatePlataformesOk.setValue(true);
                    Log.d(TAG, "updateinfo code: "+response.code());
                }
                else {
                    mCreatePlataformesOk.setValue(false);
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

    public MutableLiveData<Boolean> getmCreatePlataformesOk() {
        return mCreatePlataformesOk;
    }

    public void delete_platform(Plataformes plataformes){
        Log.d(TAG,"Entrant deleteplatform");
        plataformesService.delete_platform(plataformes, plataformes.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"delete plataformes response"+response.code()+response.errorBody());
                int code = response.code();
                if (code == 200){
                    mResponseDeletePlatform.setValue(R.string.delete_platform_ok);
                }
                else{
                    mResponseDeletePlatform.setValue(R.string.delete_platform_error);
                    Log.d(TAG, "delete jocs error"+response.code()+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mResponseDeletePlatform.setValue(R.string.delete_platform_error);
                Log.d(TAG, "delete plataformes error"+t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Integer> getmResponseDeletePlatform(){
        return mResponseDeletePlatform;
    }

}
