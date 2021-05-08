package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class EditAccountViewModel {

    AccountRepo accountRepo;
    String TAG = "EditAccountViewModel";
    public MutableLiveData<Boolean> responseUpdate;
    public MutableLiveData<Account> m_Account;

    public EditAccountViewModel(){
        accountRepo = new AccountRepo();
        m_Account = new MutableLiveData<>();
        m_Account = accountRepo.getmAccountInfo();
    }

    public void uploadAccountImage(File imageFile){
        Log.d("VM", "uploading image... using repo");
        this.accountRepo.uploadPhoto(imageFile);
    }

    public void save_and_exit(){
        Log.d(TAG, "save_and_exit");
        //Account account =m_Account.getValue();
        GenereEnum g = GenereEnum.N;
        /*
        switch (spinner.getSelectedItemPosition()){
            case 0:
                g=GenereEnum.M;
                break;
            case 1:
                g=GenereEnum.F;
                break;
            case 2:
                g=GenereEnum.NB;
                break;
            case 3:
                g=GenereEnum.N;
                break;
        }

         */
        /*
        account.setGenere(g);
        accountRepo.updateAccount(account);

         */
        //responseUpdate.setValue(accountRepo.getmUpdateOk().getValue());
    }

    public void update_info_from_db(){
        Log.d(TAG, "Update info from db");
        //m_Account.setValue(accountRepo.getmAccountInfo().getValue());
    }

    public MutableLiveData<Account> getm_Account(){
        return accountRepo.getmAccountInfo();
    }
}
