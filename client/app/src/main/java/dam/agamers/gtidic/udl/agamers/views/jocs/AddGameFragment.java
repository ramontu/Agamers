package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Collections;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class AddGameFragment extends Fragment {

    private AddGameViewModel addGameViewModel;
    private EditText nomjoc_edit, buscarcate_edit;
    private View root;

    public void initView(){
        nomjoc_edit = root.findViewById(R.id.nom_joc_edit);
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

    //Linkar con button en el layout
    public void createJocOnClick(View view){
        Jocs j = new Jocs();
        j.setName(nomjoc_edit.getText().toString());
        j.setCategories(Collections.singletonList(buscarcate_edit.getText().toString()));

        //per cada text fer un j. 
        addGameViewModel.createJoc();
    }

    public void onBackPressed(){
        NavHostFragment.findNavController((AddGameFragment.this)).navigate(R.id.action_fragmentaddgame_to_fragmentjocs);
    }
}