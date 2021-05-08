package dam.agamers.gtidic.udl.agamers.services.profile;

import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ProfileServiceImpl implements ProfileService{

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    @Override

    public Call<Profile> download_user_info() {

        return retrofit.create(ProfileService.class).download_user_info();

    }
}
