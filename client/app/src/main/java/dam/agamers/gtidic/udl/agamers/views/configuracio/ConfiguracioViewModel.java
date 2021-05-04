package dam.agamers.gtidic.udl.agamers.views.configuracio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConfiguracioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inici fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
