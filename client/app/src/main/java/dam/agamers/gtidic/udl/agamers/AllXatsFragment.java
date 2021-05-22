package dam.agamers.gtidic.udl.agamers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter;

public class AllXatsFragment extends Fragment {

    private AllXatsViewModel mViewModel;


    //FIREBASE
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private MessageAdapter adapter;


    //LAYOUT
    LinearLayoutManager manager;

    //BINDING

    public static AllXatsFragment newInstance() {
        return new AllXatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.all_xats_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllXatsViewModel.class);
        // TODO: Use the ViewModel

        //Iniciar sessió a la bbdd
        //RETORNA NULL si no ha acoseguit entrar a la bbdd i el user si si que ha entrat
        auth = mViewModel.signIn_Firebase();
    }
}