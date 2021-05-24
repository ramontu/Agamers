package dam.agamers.gtidic.udl.agamers.services.categories;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Categories;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoriesService {

    @GET("/categories")
    Call<List<Categories>> download_categories_info();

    @POST("/category/create")
    Call<ResponseBody> create_category(@Body Categories categories);
}
