package dam.agamers.gtidic.udl.agamers.views.notificacions;

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

public class NotificacionsFragment extends Fragment {

    private NotificacionsViewModel notificacionsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificacionsViewModel =
                new ViewModelProvider(this).get(NotificacionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notificacions, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        notificacionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}