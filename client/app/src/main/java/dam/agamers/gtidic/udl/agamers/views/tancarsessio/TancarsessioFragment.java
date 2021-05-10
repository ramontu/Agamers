package dam.agamers.gtidic.udl.agamers.views.tancarsessio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;
import java.util.zip.Inflater;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;

public class TancarsessioFragment extends Fragment {

    private TancarsessioViewModel tancarsessioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tancarsessioViewModel =
                new ViewModelProvider(this).get(TancarsessioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inici, container, false);
        //final TextView textView = root.findViewById(R.id.text_inici);

        return root;


        //inflar un dialeg de confirmació de tancar sessió
    }

    //NO: tornar a principal
    //SI tancar()



    public void tancar(){
        tancarsessioViewModel.close_session().observe(this, aBoolean -> {
            if (aBoolean){
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
                requireActivity().finishAffinity();
            }
        });
    }
}