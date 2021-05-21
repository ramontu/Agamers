package dam.agamers.gtidic.udl.agamers;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;

public class allxatmessages extends Fragment {

    private AllxatmessagesViewModel mViewModel;



    public static allxatmessages newInstance() {
        return new allxatmessages();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.allxatmessages_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllxatmessagesViewModel.class);
        // TODO: Use the ViewModel
    }

}