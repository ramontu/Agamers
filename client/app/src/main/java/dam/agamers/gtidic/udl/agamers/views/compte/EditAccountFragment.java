package dam.agamers.gtidic.udl.agamers.views.compte;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.R;

public class EditAccountFragment extends Fragment {

    EditAccountViewModel editAccountViewModel;

    public EditAccountFragment() {

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editAccountViewModel =
                new ViewModelProvider(this).get(EditAccountViewModel.class);

        return inflater.inflate(R.layout.fragment_edit_account, container, false);
    }

    public void InitView(){

    }
}