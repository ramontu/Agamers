package dam.agamers.gtidic.udl.agamers.views.compte;

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

import dam.agamers.gtidic.udl.agamers.R;

public class CompteFragment extends Fragment {

    private CompteViewModel compteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        compteViewModel =
                new ViewModelProvider(this).get(CompteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_compte, container, false);
        final TextView textView = root.findViewById(R.id.text_compte);
        compteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}