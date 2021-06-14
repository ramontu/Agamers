package dam.agamers.gtidic.udl.agamers.views.compte;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class EditAccountViewModel extends ViewModel {

    AccountRepo accountRepo;
    String TAG = "EditAccountViewModel";
    public MutableLiveData<Boolean> responseUpdate;
    public MutableLiveData<Account> m_Account = new MutableLiveData<>();

    public EditAccountViewModel(){
        accountRepo = new AccountRepo();
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

        //account.setGenere(g);
        //accountRepo.updateAccount();



        //responseUpdate.setValue(accountRepo.getmUpdateOk().getValue());
    }



    public void update_info_from_db(){
        //TODO no esta fent res
        Log.d(TAG, "Update info from db");
        //m_Account.setValue(accountRepo.getmAccountInfo().getValue());
    }

    public MutableLiveData<Account> getm_Account(){
        return accountRepo.getmAccountInfo();
    }

}
