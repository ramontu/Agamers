package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import javax.security.auth.callback.Callback;

import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.services.profile.ProfileService;
import retrofit2.Call;
import retrofit2.Response;

public class ProfileRepo {
    private final String TAG = "ProfileRepo";

    private final ProfileService profileService;

    private MutableLiveData<Profile> mProfileInfo;

    public ProfileRepo(ProfileService profileService) {
        this.profileService = profileService;
        this.mProfileInfo=new MutableLiveData<>();
    }

    private void download_user_info(){
        profileService.download_user_info().enqueue(new Callback<Profile>() {

            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                mProfileInfo.setValue(response.body());

                Log.d(TAG, "DownloadInfo() : "+response.code() +"joc:"+response.body().toString());

            }



            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

                Log.d(TAG, "DownloadInfo() : Error"+ t.getMessage());

                t.printStackTrace();

            }

        });

    }
    public MutableLiveData<Profile> getmProfileInfo(){

        download_user_info();

        return mProfileInfo;

    }
}


