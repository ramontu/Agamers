package dam.agamers.gtidic.udl.agamers;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dam.agamers.gtidic.udl.agamers.databinding.ActivityIniciDeSessioBinding;
import dam.agamers.gtidic.udl.agamers.databinding.XatFragmentBinding;
import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;

public class Xat extends Fragment {

    private XatViewModel mViewModel;
    FloatingActionButton floatingActionButton;

    public static Xat newInstance() {
        return new Xat();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState, int viewType) {
        //floatingActionButton = container.findViewById(R.id.floatingActionButtonsendmessage);
        XatFragmentBinding xatFragmentBinding =
                DataBindingUtil.setContentView((FirstActivity) getActivity(), R.layout.message);
        xatFragmentBinding.setLifecycleOwner(this);
        xatFragmentBinding.setViewModel(mViewModel);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue( new Message("prova1", "provador", "none", "none"),
                                FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName());
            }
        });

        return inflater.inflate(R.layout.xat_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(XatViewModel.class);
        // TODO: Use the ViewModel
    }



}