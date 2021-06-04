package dam.agamers.gtidic.udl.agamers.network;

import java.io.IOException;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ServiceInterceptor implements Interceptor {
    @EverythingIsNonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String token = PreferencesProvider.providePreferences().getString("token","");
        System.out.println(token);
        if(request.header("No-Authentication") == null){
            request = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build();
        }
        return chain.proceed(request);
    }
}
