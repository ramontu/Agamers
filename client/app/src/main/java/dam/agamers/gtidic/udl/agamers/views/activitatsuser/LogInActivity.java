package dam.agamers.gtidic.udl.agamers.views.activitatsuser;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.databinding.ActivityIniciDeSessioBinding;
import dam.agamers.gtidic.udl.agamers.viewmodels.LogInViewModel;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;
import dam.agamers.gtidic.udl.agamers.views.recovery.RecoverPasswordActivity_1;

public class LogInActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        InitView();
    }

    protected void InitView(){
        //Components
        LogInViewModel logInViewModel = new LogInViewModel();
        ActivityIniciDeSessioBinding activityLogInBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_inici_de_sessio);
        activityLogInBinding.setLifecycleOwner(this);
        activityLogInBinding.setViewModel(logInViewModel);

        logInViewModel.getResponseLogin().observe(this, s -> {
            if (s.contains("Error: ")){
                showInfoUser(getCurrentFocus(), getString(R.string.login_error));
            }
            else {
                showInfoUser(getCurrentFocus(), getString(R.string.login_succes));
                goTo(FirstActivity.class);
            }
        });

    }

    public void onSingUp(View view){
        goTo(SignUpActivity.class);
    }

    public void onPasswordRecovery(View view){
        goTo(RecoverPasswordActivity_1.class);
    }

    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.alerts_exit_app_title);
        alertDialogBuilder.setMessage(R.string.alerts_exit_app_message);
        alertDialogBuilder.setNegativeButton(R.string.option_cancell, (dialog, which) -> {

        });
        alertDialogBuilder.setPositiveButton(R.string.option_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}