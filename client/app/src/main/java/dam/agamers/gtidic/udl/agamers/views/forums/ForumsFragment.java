package dam.agamers.gtidic.udl.agamers.views.forums;

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


public class ForumsFragment extends Fragment {

    private ForumsViewModel forumsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumsViewModel = new ViewModelProvider(this).get(ForumsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forums, container, false);
        final TextView textView = root.findViewById(R.id.text_forums);
        forumsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}