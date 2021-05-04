package dam.agamers.gtidic.udl.agamers.views.botiga;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BotigaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BotigaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inici fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
