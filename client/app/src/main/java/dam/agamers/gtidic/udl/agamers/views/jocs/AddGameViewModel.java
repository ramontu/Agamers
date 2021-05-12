package dam.agamers.gtidic.udl.agamers.views.jocs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddGameViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddGameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is addgame fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
