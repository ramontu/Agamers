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


    //private MutableLiveData<Account> mAccount = new MutableLiveData<>();

    /*
    public MutableLiveData<String> mUsername = new MutableLiveData<>();
    public MutableLiveData<AccountTypeEnum> mAccount_type = new MutableLiveData<>(); //TODO passar a a Enum
    public MutableLiveData<String> short_description = new MutableLiveData<>();
    public MutableLiveData<String> long_description = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> surname = new MutableLiveData<>();
    public MutableLiveData<Date> birthday = new MutableLiveData<>();
    public MutableLiveData<GenereEnum> genere = new MutableLiveData<>();
    public MutableLiveData<String> photo = new MutableLiveData<>(); //TODO COMPLETAR AMB LA PHOTO

     */



    public EditAccountViewModel(){
        accountRepo = new AccountRepo();
        accountRepo.download_user_info();

    }


    public MutableLiveData<Account> getmAccount(){
        return accountRepo.getmAccountInfo();
    }

    public void uploadAccountImage(File imageFile){
        Log.d("VM", "uploading image... using repo");
        this.accountRepo.uploadPhoto(imageFile);
    }
}
