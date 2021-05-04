package dam.agamers.gtidic.udl.agamers.views.tancarsessio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TancarsessioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TancarsessioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tancar sessió fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
