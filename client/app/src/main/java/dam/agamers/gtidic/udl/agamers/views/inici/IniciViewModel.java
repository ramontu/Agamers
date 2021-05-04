package dam.agamers.gtidic.udl.agamers.views.inici;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IniciViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public IniciViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inici fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
