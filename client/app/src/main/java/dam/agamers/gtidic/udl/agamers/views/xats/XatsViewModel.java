package dam.agamers.gtidic.udl.agamers.views.xats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class XatsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public XatsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is xat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
