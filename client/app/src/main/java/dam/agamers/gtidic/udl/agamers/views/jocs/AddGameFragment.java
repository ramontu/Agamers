package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class AddGameFragment extends Fragment {

    private final static String TAG="AddGameFragment";

    private AddGameViewModel addGameViewModel;
    private EditText nomjoc_edit, buscarcate_edit, maxplayer_edit, minplayer_edit, descripciojoc_edit, edatrecomanada_edit, datapublicacio_edit, plataformes_edit, estudio_edit;
    private View root;
    private Button crearJocButton;
    private final int PICK_IMAGE_REQUEST = 14;

    //@TODO: Validació dels camps.
    //@TODO: Mostra toast de info al usuari al rebre el 200 (esto esta en first activity)
    //@TODO: Torna a la pantalla de la llista de jocs
    //@TODO: Spinners de categories i plataformes (en edit info hay algo parecido)

    public void initView(){
        nomjoc_edit = root.findViewById(R.id.nom_joc_edit);
        maxplayer_edit = root.findViewById(R.id.max_players_edit);
        minplayer_edit = root.findViewById(R.id.min_players_edit);
        descripciojoc_edit = root.findViewById(R.id.descripcio_edit);
        edatrecomanada_edit = root.findViewById(R.id.pegi_edit);
        datapublicacio_edit= root.findViewById(R.id.data_publicacio);
        estudio_edit = root.findViewById(R.id.studio_edit);
        crearJocButton = root.findViewById(R.id.crear_joc);

        addGameViewModel.jocIsCreated().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    // Levantar el toast para informar que se ha creado correctamente
                }
            }
        });
        crearJocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jocs j = new Jocs();
                j.setName(nomjoc_edit.getText().toString());
                //j.setCategories(Collections.singletonList(buscarcate_edit.getText().toString()));
                j.setMax_players(Integer.parseInt(maxplayer_edit.getText().toString()));
                j.setMin_players(Integer.parseInt(minplayer_edit.getText().toString()));
                j.setDescription(descripciojoc_edit.getText().toString());
                j.setPegi(Integer.parseInt(edatrecomanada_edit.getText().toString()));
                j.setPublished(datapublicacio_edit.getText().toString());
                j.setStudio(estudio_edit.getText().toString());
                j.setCategories(new ArrayList<String>());
                j.setPlatforms(new ArrayList<String>());

                Log.d(TAG, j.toString());

                //per cada text fer un j.
                addGameViewModel.createJoc(j);
            }
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle saveInstanceState) {

        addGameViewModel = new ViewModelProvider(this).get(AddGameViewModel.class);
        root = inflater.inflate(R.layout.fragment_addgame, container, false);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    public void onBackPressed(){
        NavHostFragment.findNavController((AddGameFragment.this)).navigate(R.id.action_fragmentaddgame_to_fragmentjocs);
    }


}