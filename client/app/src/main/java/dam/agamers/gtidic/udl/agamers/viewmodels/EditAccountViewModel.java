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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
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
