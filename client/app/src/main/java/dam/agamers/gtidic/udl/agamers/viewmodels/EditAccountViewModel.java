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
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.Date;
import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.enums.AccountTypeEnum;
import dam.agamers.gtidic.udl.agamers.models.enums.GenereEnum;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class EditAccountViewModel {

    AccountRepo accountRepo;
    String TAG = "EditAccountViewModel";

    private MutableLiveData<Account> accountMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> account_type = new MutableLiveData<>(); //TODO passar a a Enum
    public MutableLiveData<String> short_description = new MutableLiveData<>();
    public MutableLiveData<String> long_description = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> surname = new MutableLiveData<>();
    public MutableLiveData<Date> birthday = new MutableLiveData<>();
    public MutableLiveData<GenereEnum> genere = new MutableLiveData<>();
    public MutableLiveData<String> photo = new MutableLiveData<>(); //TODO COMPLETAR AMB LA PHOTO



    public EditAccountViewModel(){
        accountRepo = new AccountRepo();

    }


    public void setParameters(){
        accountRepo.download_user_info();
        Account account = accountRepo.getmAccountInfo().getValue();
        if (account != null){
            Log.d(TAG, "SetParameters");
            username.setValue(account.getUsername());
            account_type.setValue(AccountTypeEnum.valueOf(account.getAccount_type().toString()).toString());
        }
        else {
            //TODO mostrar missatge d'error al mostrar la informació
        }


    }


    public void uploadAccountImage(File imageFile){
        Log.d("VM", "uploading image... using repo");
        this.accountRepo.uploadPhoto(imageFile);
    }


}
