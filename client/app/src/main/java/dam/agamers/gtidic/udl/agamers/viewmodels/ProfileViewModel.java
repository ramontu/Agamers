package dam.agamers.gtidic.udl.agamers.viewmodels;

import androidx.lifecycle.MutableLiveData;

import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.ProfileRepo;

public class ProfileViewModel {

    private MutableLiveData<Profile> profile;
    private ProfileRepo profilerepo;

    public ProfileViewModel() {
        profile = new MutableLiveData<>();
        profilerepo = new ProfileRepo();
    }

    public void getProfile(){
        String token= PreferencesProvider.providePreferences().getString("token", "");
        profilerepo.download_user_info(token);
    }
    public MutableLiveData<Profile> agafarProfile(){
        return profilerepo.getmProfileInfo();
    }

}
