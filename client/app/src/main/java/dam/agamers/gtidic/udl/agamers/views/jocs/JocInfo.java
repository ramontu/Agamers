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
import dam.agamers.gtidic.udl.agamers.models.Jocs;


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

        Bundle b = getArguments();
        if (b != null){
            Jocs joc = b.getParcelable("joc");
            displayReceivedGame(joc);
        }

        return root;
    }

    public void displayReceivedGame(Jocs joc) {
            info_nomjoc.setText(joc.getName());
            info_descripcio.setText(joc.getName());
    }

}