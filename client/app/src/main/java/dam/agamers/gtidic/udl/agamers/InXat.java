package dam.agamers.gtidic.udl.agamers;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;

public class InXat extends Fragment {

    private InxatViewModel mViewModel;
    DataSnapshot chat_snapshot;

    public InXat(DataSnapshot chat_snapshot){
        this.chat_snapshot = chat_snapshot;
    }

    public InXat(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Desactivem barra inferior
        getActivity().findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);
        return inflater.inflate(R.layout.inxat_fragment, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InxatViewModel.class);
        // TODO: Use the ViewModel

    }
}