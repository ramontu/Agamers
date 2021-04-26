package dam.agamers.gtidic.udl.agamers.viewmodels;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class MainActivityViewModel {

    public MainActivityViewModel() {
    }

    public Boolean isCurrentLogIn(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        return !token.equals("");
    }
}
