package dam.agamers.gtidic.udl.agamers.views.jocs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.repositories.JocsRepo;

public class JocsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private JocsRepo jocsRepo;

    public JocsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is jocs fragment");
        jocsRepo = new JocsRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getJocs() {
        jocsRepo.getJocs();
    }

    public MutableLiveData<List<Jocs>> returnJocs() {
        return this.jocsRepo.getmResponseJocs();
    }

}
