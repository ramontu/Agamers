package dam.agamers.gtidic.udl.agamers.views.jocs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Jocs;

public class JocsFragment extends Fragment {

    private JocsViewModel jocsViewModel;
    private View root;
    private RecyclerView recyclerView;
    private JocAdapter jocAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jocsViewModel = new ViewModelProvider(this).get(JocsViewModel.class);
        root = inflater.inflate(R.layout.fragment_jocs, container, false);


        recyclerView = root.findViewById(R.id.jocsRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));

        jocAdapter = new JocAdapter(new JocDiffCallBack());
        recyclerView.setAdapter (jocAdapter);
        initView();

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton addButton = root.findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(JocsFragment.this)
                        .navigate(R.id.action_fragmentjocs_to_fragmentaddgame);
            }
        });
    }

    public void initView () {
        refresh();
        jocsViewModel.returnJocs().observe(getViewLifecycleOwner(), jocs -> {
            jocAdapter.submitList(jocs);
            refresh();
        });
        jocAdapter.setOnItemClickListener(new JocAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Jocs joc) {
                NavHostFragment.findNavController(JocsFragment.this)
                        .navigate(R.id.action_fragmentjocs_to_fragmentainfojoc);

            }
        });

    }



    public void refresh(){
        jocsViewModel.getJocs();
    }


}