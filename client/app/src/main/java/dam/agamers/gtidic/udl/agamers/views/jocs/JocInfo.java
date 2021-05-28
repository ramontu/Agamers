package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.views.inici.IniciViewModel;


public class JocInfo extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_infojocs, container, false);
        return root;
    }
}