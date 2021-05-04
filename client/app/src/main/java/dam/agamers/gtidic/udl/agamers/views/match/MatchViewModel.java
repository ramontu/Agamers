package dam.agamers.gtidic.udl.agamers.views.match;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MatchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}