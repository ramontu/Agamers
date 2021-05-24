package dam.agamers.gtidic.udl.agamers.views.tancarsessio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;
import java.util.zip.Inflater;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;

public class TancarsessioFragment extends Fragment {

    private TancarsessioViewModel tancarsessioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tancarsessioViewModel =
                new ViewModelProvider(this).get(TancarsessioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inici, container, false);


        //TODO inflar un dialeg de confirmació de tancar sessió
        //NO: tornar a principal
        //SI tancar()
        tancar();
        return root;
    }





    public void tancar(){
        tancarsessioViewModel.close_session().observe(getViewLifecycleOwner(), aBoolean -> {
            Log.d("Tancar", "He canviat:"+aBoolean);
            //Independentment de si es tanca bé la sessió o no s'ha de sortir
            FirstActivity firstActivity = (FirstActivity) getActivity();
            if (aBoolean){
                firstActivity.showInfoUser(getView(),getString(R.string.succes_clossing_session));
            }
            else {
                firstActivity.showInfoUser(getView(),getString(R.string.error_clossing_session));
            }
            //Al cap de 2000 milisegons crida al metode change_to_login que canvia la pantalla
            (new Handler()).postDelayed(this::change_to_login, 2000);
        });
    }

    private void change_to_login(){
        Intent intent = new Intent(getActivity(), LogInActivity.class);
        startActivity(intent);
        requireActivity().finishAffinity();
    }
}