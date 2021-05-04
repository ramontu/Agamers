package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class EditAccountViewModel {

    AccountRepo accountRepo;
    String TAG = "EditAccountViewModel";
    public MutableLiveData<Boolean> responseUpdate;


    public EditAccountViewModel(){
        accountRepo = new AccountRepo();
        accountRepo.download_user_info();
        responseUpdate = new MutableLiveData<>();
    }


    public MutableLiveData<Account> getmAccount(){
        return accountRepo.getmAccountInfo();
    }

    public void uploadAccountImage(File imageFile){
        Log.d("VM", "uploading image... using repo");
        this.accountRepo.uploadPhoto(imageFile);
    }

    public void update_info(Account account){
        Log.d(TAG, "Update info");
        accountRepo.updateAccount(account);
        responseUpdate.setValue(accountRepo.getmUpdateOk().getValue());
    }
}
