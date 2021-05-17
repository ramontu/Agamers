package dam.agamers.gtidic.udl.agamers.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    // Producción
    //private static final String BASE_URL = "http://192.168.101.86:8000";

    // Test en mi pc con docker levantado
    // Esta ip se consigue compartiendo internet con el tel. y cmd ipconfig
    // Asegurar tener los puertos abiertos en el firewall de windows y la vpn cerrada
    private static final String BASE_URL = "http://192.168.43.250:8000";


    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ServiceInterceptor()).build();

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
