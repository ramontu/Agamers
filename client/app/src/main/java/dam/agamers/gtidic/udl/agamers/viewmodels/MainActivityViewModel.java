package dam.agamers.gtidic.udl.agamers.viewmodels;

import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class MainActivityViewModel {

    public MainActivityViewModel() {
    }

    /**
     * @return retorna el token 
     */
    public Boolean isCurrentLogIn(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        return !token.equals("");
    }
}
