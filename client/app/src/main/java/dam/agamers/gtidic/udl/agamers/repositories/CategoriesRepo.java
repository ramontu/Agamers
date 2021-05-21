package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Categories;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.services.categories.CategoriesService;
import dam.agamers.gtidic.udl.agamers.services.categories.CategoriesServiceImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesRepo {

    private final String TAG = "CategoriesRepo";
    private final CategoriesService categoriesService;

    private MutableLiveData<List<Categories>> mCategoriesInfo;
    private MutableLiveData<Boolean> mCreateCategoriesOk;

    public CategoriesRepo(){
        this.categoriesService = new CategoriesServiceImpl();
        this.mCategoriesInfo = new MutableLiveData<>();
        this.mCreateCategoriesOk = new MutableLiveData<>();
    }

    public void download_categories_info(){
        categoriesService.download_categories_info().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List <Categories>> response) {
                mCategoriesInfo.setValue(response.body());
                Log.d(TAG, "DownloadInfo() : " +response.code() +"categoria:" +response.body().toString());
            }
            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.d(TAG, "DownloadInfo() : Error" + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void create_categories(Categories categories){
        Log.d(TAG,"update info");
        categoriesService.create_category(categories).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    mCreateCategoriesOk.setValue(true);
                    Log.d(TAG, "updateinfo code: "+response.code());
                }
                else {
                    mCreateCategoriesOk.setValue(false);
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

    public MutableLiveData<List<Categories>> getmCategoriesInfo() {
        return mCategoriesInfo;
    }
}
