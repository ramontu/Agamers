package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class AddGameFragment extends Fragment {

    private AddGameViewModel addGameViewModel;
    private EditText nomjoc_edit;
    private View root;

    public void initView(){
        nomjoc_edit = root.findViewById(R.id.nom_joc);
    }

    public void updateJoc(){
        addGameViewModel.m_Jocs.observe(getViewLifecycleOwner(), new Observer<Jocs>() {
            @Override
            public void onChanged(Jocs jocs) {
                nomjoc_edit.setText(jocs.getName());
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle saveInstanceState) {
        addGameViewModel = new AddGameViewModel();
        root = inflater.inflate(R.layout.fragment_addgame, container, false);
        final TextView textView = root.findViewById(R.id.text_jocs);
        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        updateJoc();
    }

    public void onBackPressed(){
        NavHostFragment.findNavController((AddGameFragment.this)).navigate(R.id.action_fragmentaddgame_to_fragmentjocs);
    }
}