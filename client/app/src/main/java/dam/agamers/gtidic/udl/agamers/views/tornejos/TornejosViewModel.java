package dam.agamers.gtidic.udl.agamers.views.tornejos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TornejosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TornejosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tornejos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
