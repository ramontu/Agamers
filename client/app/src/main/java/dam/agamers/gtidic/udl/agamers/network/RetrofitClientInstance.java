package dam.agamers.gtidic.udl.agamers.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.101.86:8000";
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ServiceInterceptor()).build();

    /**
     * @return retorna la connexió amb el client.
     */

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callFactory(client)
                    .build();
        }
        return retrofit;
    }
}
