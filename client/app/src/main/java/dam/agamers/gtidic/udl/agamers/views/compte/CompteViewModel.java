package dam.agamers.gtidic.udl.agamers.views.compte;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CompteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is compte fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
