package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Categories;
import dam.agamers.gtidic.udl.agamers.models.Jocs;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;

public class AddGameFragment extends Fragment {

    private final static String TAG="AddGameFragment";

    private AddGameViewModel addGameViewModel;
    private JocsViewModel jocsViewModel;
    private EditText nomjoc_edit,maxplayer_edit, minplayer_edit, descripciojoc_edit, edatrecomanada_edit, datapublicacio_edit, plataformes_edit, estudio_edit;
    private View root;
    private Button crearJocButton;
    private final int PICK_IMAGE_REQUEST = 14;
    private Spinner categories_spinner;

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
        categories_spinner = (Spinner) root.findViewById(R.id.categories);

        jocsViewModel.getCategories();

        jocsViewModel.returnCategories().observe(getViewLifecycleOwner(), new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                List<String> categories_names = new ArrayList<>(categories.size());
                for (Categories category : categories) {
                    categories_names.add(category.getName());
                }
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        categories_names, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                categories_spinner.setAdapter(adapter);

            }
        });

        addGameViewModel.jocIsCreated().observe(getViewLifecycleOwner(),
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        FirstActivity firstActivity = (FirstActivity) getActivity();
                        if (aBoolean){
                            firstActivity.showInfoUser(getView(),getString(R.string.succes_clossing_session));
                        }//cambiar el valor de las strings
                        else {
                            firstActivity.showInfoUser(getView(),getString(R.string.error_clossing_session));
                        }
                        //Al cap de 2000 milisegons crida al metode change_to_login que canvia la pantalla
                        (new Handler()).postDelayed(this::navegacioJoc, 2000);
                    }

                    private void navegacioJoc() {
                        NavHostFragment.findNavController((AddGameFragment.this)).navigate(R.id.action_fragmentaddgame_to_fragmentjocs);
                    }

                });

        crearJocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jocs j = new Jocs();
                j.setName(nomjoc_edit.getText().toString());
                j.setMax_players(Integer.parseInt(maxplayer_edit.getText().toString()));
                j.setMin_players(Integer.parseInt(minplayer_edit.getText().toString()));
                j.setDescription(descripciojoc_edit.getText().toString());
                j.setPegi(Integer.parseInt(edatrecomanada_edit.getText().toString()));
                j.setPublished(datapublicacio_edit.getText().toString());
                j.setStudio(estudio_edit.getText().toString());
                j.setCategories(new ArrayList<String>());
                j.setPlatforms(new ArrayList<String>());

                Log.d(TAG, j.toString());
                addGameViewModel.createJoc(j);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle saveInstanceState) {

        addGameViewModel = new ViewModelProvider(this).get(AddGameViewModel.class);
        jocsViewModel = new ViewModelProvider(this).get(JocsViewModel.class);
        root = inflater.inflate(R.layout.fragment_addgame, container, false);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    
}
