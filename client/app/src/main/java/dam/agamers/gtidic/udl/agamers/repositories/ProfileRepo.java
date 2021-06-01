package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.services.profile.ProfileService;
import dam.agamers.gtidic.udl.agamers.services.profile.ProfileServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepo {
    private final String TAG = "ProfileRepo";

    private final ProfileService profileService;

    private MutableLiveData<Profile> mProfileInfo;





    public ProfileRepo() {
        this.profileService = new ProfileServiceImpl();
        this.mProfileInfo= new MutableLiveData<>();
    }

    public void download_user_info(String token){
        //el admin està hardcodejat, cal agafar-lo de la variable que toqui
        profileService.download_user_info(token, "admin").enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.code() == 200){
                    mProfileInfo.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });

    }
    public MutableLiveData<Profile> getmProfileInfo(){

        return mProfileInfo;

    }
}


