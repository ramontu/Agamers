package dam.agamers.gtidic.udl.agamers.views.jocs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JocsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public JocsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is jocs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
