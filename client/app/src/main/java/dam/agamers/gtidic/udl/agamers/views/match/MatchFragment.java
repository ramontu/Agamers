package dam.agamers.gtidic.udl.agamers.views.match;

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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;


public class MatchFragment extends Fragment {

    private MatchViewModel matchViewModel;
    private View root;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        matchViewModel =
                new ViewModelProvider(this).get(MatchViewModel.class);
        root = inflater.inflate(R.layout.fragment_match, container, false);
        recyclerView = root.findViewById(R.id.matchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        matchAdapter = new MatchAdapter(new MatchDiffCallback());
        recyclerView.setAdapter (matchAdapter);
        initView();
        return root;
    }


    public void initView () {

        refresh();

        matchViewModel.returnAccount().observe(getViewLifecycleOwner(), account -> {

            matchAdapter.submitList(account);


        });
/*
        matchAdapter.setOnItemClickListener(new MatchAdapter.OnItemClickListener() {

            @Override

            public void onItemClick(Account account) {

                Bundle b = new Bundle();

                b.putParcelable("account", account);

                NavHostFragment.findNavController(MatchFragment.this)

                        .navigate(R.id.action_fragmentjocs_to_fragmentainfojoc, b);

            }


        });
*/
    }



    public void refresh(){

        matchViewModel.getInfoMatch();

    }

}