package dam.agamers.gtidic.udl.agamers.views.match;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class MatchViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private AccountRepo accountRepo;

    public MatchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is match fragment");
        accountRepo = new AccountRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }
    public void getInfoMatch() {
        accountRepo.getInfoMatch();
    }

    public MutableLiveData<List<Account>> returnAccount() {

        return this.accountRepo.getmResponseMatch();

    }
}