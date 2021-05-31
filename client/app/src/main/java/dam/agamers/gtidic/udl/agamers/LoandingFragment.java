package dam.agamers.gtidic.udl.agamers;

import android.app.Activity;

import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;


public class LoandingFragment extends DialogFragment {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoandingFragment(Activity activity) {
        this.activity= activity;
    }

    public void startLoadingDialog()
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_loanding,null));
        builder.setCancelable(false);


        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dismisDialog()
    {
        alertDialog.dismiss();
    }
}
