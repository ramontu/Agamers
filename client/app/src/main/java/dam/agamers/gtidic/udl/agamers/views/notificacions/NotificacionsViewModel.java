package dam.agamers.gtidic.udl.agamers.views.notificacions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificacionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificacionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
