package dam.agamers.gtidic.udl.agamers.views.favorits;

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
import dam.agamers.gtidic.udl.agamers.views.forums.ForumsViewModel;


public class FavoritsFragment extends Fragment {

    private FavoritsViewModel favoritsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritsViewModel = new ViewModelProvider(this).get(FavoritsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorits, container, false);
        final TextView textView = root.findViewById(R.id.text_favorits);
        favoritsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}