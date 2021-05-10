package dam.agamers.gtidic.udl.agamers.views.tancarsessio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;

public class TancarsessioViewModel extends ViewModel {


    public TancarsessioViewModel() {

    }


    public MutableLiveData<Boolean> close_session(){
        AccountRepo accountRepo = new AccountRepo();
        accountRepo.deleteToken();
        return accountRepo.getmDeleteTokenOk();
    }


}
