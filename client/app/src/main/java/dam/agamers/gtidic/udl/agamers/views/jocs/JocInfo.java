package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;


public class JocInfo extends Fragment {

    private TextView info_nomjoc, info_categoriajoc, info_jugadors, info_modalitat, info_estudio, info_plataformes, info_descripcio, info_edatrecomanada, info_datapublicacio, result;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_infojocs, container, false);
        //aqui haig de fer el findbyid de tots els elements de la vista .setText
        info_nomjoc = root.findViewById(R.id.info_nomjoc);
        info_categoriajoc = root.findViewById(R.id.info_categoriajoc);
        info_jugadors = root.findViewById(R.id.info_jugadors);
        info_modalitat = root.findViewById(R.id.info_modalitat);
        info_estudio = root.findViewById(R.id.info_estudio);
        info_plataformes = root.findViewById(R.id.info_plataformes);
        info_descripcio = root.findViewById(R.id.info_descripcio);
        info_edatrecomanada = root.findViewById(R.id.info_edatrecomanada);
        info_datapublicacio = root.findViewById(R.id.info_datapublicacio);

        return root;

        result.setText("Nom joc: " + info_nomjoc + "Categories: " + info_categoriajoc + "Jugadors: " +info_jugadors+);

        //buscar en google com passar informació entre fragments fer servir un bundle
        //https://developer.android.com/training/basics/fragments/pass-data-between?hl=es

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle bundle) {
                String result = bundle.getString("bundleKey");
            }
        });


    }
}