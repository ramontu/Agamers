package dam.agamers.gtidic.udl.agamers.views.xats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class XatsViewModel extends ViewModel {

    private GoogleSignInClient signInClient;
    //TODO Binding
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    //TODO adapter de la clase friendlymessageadapter del codelab

    private MutableLiveData<String> mText;

    public XatsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is xat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
