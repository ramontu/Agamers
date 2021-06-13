package dam.agamers.gtidic.udl.agamers.views.xats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.adapters.ChatAdapter;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class allxatsfragment extends Fragment {

    private AllXatsViewModel mViewModel;

    private RecyclerView recyclerView;

    private ChatAdapter chatAdapter;


    //BINDING
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AllXatsViewModel.class);
        //Activem la barra inferior i treiem la icona
        requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        toolbar.setLogo(null);

        //Comprovem si hi ha el id i el username guardat ja que s'utilitza per a determinar qui ets al firebase
        if (!PreferencesProvider.providePreferences().contains("id") || !PreferencesProvider.providePreferences().contains("username")) {
            System.out.println("Obtenint accountinfo");
            AccountRepo a = new AccountRepo();
            MutableLiveData<Account> b = a.getmAccountInfo();
            b.observe(getViewLifecycleOwner(), account -> {
                PreferencesProvider.providePreferences().edit().putInt("id", account.getId()).apply();
                PreferencesProvider.providePreferences().edit().putString("username", account.getUsername()).apply();
            });
        }

        View root = inflater.inflate(R.layout.fragment_all_xats, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        chatAdapter = new ChatAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(chatAdapter);

        initView();
        return root;
    }


    public void initView(){
        mViewModel.getChats().observe(getViewLifecycleOwner(), chats -> {
            System.out.println("Descarregant chats");
            chatAdapter.addChats(chats);
            recyclerView.setAdapter(chatAdapter);
        });

        chatAdapter.setOnItemClickListener(chat -> {
            Bundle b = new Bundle();
            b.putString("ref", chat.getSelf().getRef().getKey());
            b.putString("name", chat.getName());
            NavHostFragment.findNavController(allxatsfragment.this)
                    .navigate(R.id.action_navegacio_xats_to_inxat, b);
        });
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ONcreate");
    }
}