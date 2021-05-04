package dam.agamers.gtidic.udl.agamers.views.favorits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favorits fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}