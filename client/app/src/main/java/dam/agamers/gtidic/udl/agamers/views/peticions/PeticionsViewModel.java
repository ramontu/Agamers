package dam.agamers.gtidic.udl.agamers.views.peticions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PeticionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PeticionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}