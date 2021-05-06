package dam.agamers.gtidic.udl.agamers.viewmodels;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.enums.AccountTypeEnum;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.views.EditAccountActivity;

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
