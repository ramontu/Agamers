package dam.agamers.gtidic.udl.agamers.views.peticions;

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

public class PeticionsFragment extends Fragment {

    private PeticionsViewModel peticionsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        peticionsViewModel =
                new ViewModelProvider(this).get(PeticionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_peticions, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        peticionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}