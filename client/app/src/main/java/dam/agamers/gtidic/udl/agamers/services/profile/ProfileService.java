package dam.agamers.gtidic.udl.agamers.services.profile;

import dam.agamers.gtidic.udl.agamers.models.Profile;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileService {

    @GET("/users/show/{username}")
    Call<Profile> download_user_info();



}
