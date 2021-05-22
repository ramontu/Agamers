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

import dam.agamers.gtidic.udl.agamers.views.FirstActivity;

public class MessageFragment extends Fragment {

    private MessageFragmentViewModel mViewModel;
    FloatingActionButton floatingActionButton;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState, int viewType) {

        mViewModel =
                new ViewModelProvider(this).get(MessageFragmentViewModel.class);

        View root = inflater.inflate(R.layout.all_xats_fragment, container, R.layout.message);
        //floatingActionButton = container.findViewById(R.id.floatingActionButtonsendmessage);
        MessageFragmentBinding xatFragmentBinding =
                DataBindingUtil.setContentView((FirstActivity) getActivity(), R.layout.message);
        xatFragmentBinding.setLifecycleOwner(this);
        xatFragmentBinding.setViewModel(mViewModel);


        /*
        xatsViewModel =
                new ViewModelProvider(this).get(XatsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_xats, container, false);
        inflater_parent = inflater;
        cont = container;
        final TextView textView = root.findViewById(R.id.text_xats);
        xatsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        NavHostFragment.findNavController((XatsFragment.this)).navigate(R.id.action_navegacio_xats_to_allxatmessages); //TODO de moment
        return root;
         */

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue( new Message("prova1", "provador", "none", "none"),
                                FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName());
                                 */

            }


        });

        return inflater.inflate(R.layout.xat_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MessageFragmentViewModel.class);
        // TODO: Use the ViewModel
    }



}