package dam.agamers.gtidic.udl.agamers.services.categories;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Categories;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class CategoriesServiceImpl implements CategoriesService {

        private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

        public Call<List<Categories>> download_categories_info(){
            return retrofit.create(CategoriesService.class).download_categories_info();
        }

        public Call<ResponseBody> create_category(Categories categories){
            return retrofit.create(CategoriesService.class).create_category(categories);
        }
}
