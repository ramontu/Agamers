package dam.agamers.gtidic.udl.agamers.views.xats;

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

import dam.agamers.gtidic.udl.agamers.R;


public class XatsFragment extends Fragment {

    private XatsViewModel xatsViewModel;
    private LayoutInflater inflater_parent;
    private View cont;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        NavHostFragment.findNavController((XatsFragment.this)).navigate(R.id.action_navegacio_xats_to_allxatsfragment); //TODO de moment
        return root;
    }

    //User sign in al xat
    public void onCreate(){

    }

    public void displayOpenChats(){
        //TODO descarregar els xats que te oberts amb una altra persona i mostrar-los
    }

    public void onBackPressed(){
        NavHostFragment.findNavController((XatsFragment.this)).navigate(R.id.action_fragmentaddgame_to_fragmentjocs);
        //FirebaseAuth.getInstance().signOut();
        /*
        Inflater root = inflater_parent.inflate(R.layout.fragment_inici, (ViewGroup) cont, false);
        startActivity(root);
         */
    }



}