package dam.agamers.gtidic.udl.agamers.views.compte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.viewmodels.ProfileViewModel;
import dam.agamers.gtidic.udl.agamers.viewmodels.UserInfoViewModel;
import dam.agamers.gtidic.udl.agamers.views.FirstActivity;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.EditAccountActivity;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;
import dam.agamers.gtidic.udl.agamers.views.xats.allxatsfragment;

public class CompteFragment extends Fragment {

    private Button EnviarPet;
    private Button Xatejar;
    private Button Compartir;
    private Button update;


    private TextView usernameTextView;
    private TextView bioTextView;

    private ImageView imatgePerfil;
    private CompteViewModel compteViewModel;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        compteViewModel =
                new ViewModelProvider(this).get(CompteViewModel.class);


        root = inflater.inflate(R.layout.fragment_compte, container, false);
        Initview();
        return root;
    }

    public void Initview() {

        update = root.findViewById(R.id.update_info_button);
        /*
        EnviarPet = requireActivity().findViewById(R.id.boto_solicitud);
        Xatejar = requireActivity().findViewById(R.id.boto_xat);
        Compartir = requireActivity().findViewById(R.id.boto_compartir);
        usernameTextView = requireActivity().findViewById(R.id.usernametxt);
        bioTextView = requireActivity().findViewById(R.id.bio);
        imatgePerfil = requireActivity().findViewById(R.id.profileimage);
           */

        compteViewModel.agafarProfile().observe(getViewLifecycleOwner(), new Observer<Account>() {
            @Override
            public void onChanged(Account profile) {
                usernameTextView.setText(profile.getUsername());
                bioTextView.setText(profile.getLong_description());
                Picasso.get().load(profile.getPhoto()).into(imatgePerfil);
            }
        });

        /*
        EnviarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí hauriem d'enviar una petició d'amistat a l'usuari al qual pertany el perfil @usuari_x. Aquesta petició es guardarà en l'apartat de peticions del @usuari_x
            }
        });

        Xatejar.setOnClickListener(v -> {
            //aquí hauriem d'obrir l'apartat de xats per tal de que s'obrís el xat amb l'usuari del perfil @usuari_x. Si anteriorment ja s'havia generat aquest xat el programa ha de carregar-lo, si no haurà de generar-lo

        });
        Compartir.setOnClickListener(v -> {
            //aquí hauriem de compartir el perfil generant un link que portés al perfil d'usuari que volem compartir, fent que aquest es pogués enviar via mail, whatsapp...
        });

         */




        listeners();
    }

    private void listeners(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_edit_info();
            }
        });


    }


    private void open_edit_info(){
        NavHostFragment.findNavController(CompteFragment.this)
                .navigate(R.id.action_nav_compte_to_editAccountFragment);
    }


    public void close_session(View view){
        ((FirstActivity) requireActivity()).close_session(view);
    }

    public void delete_account (View view) {
        /*
        userInfoViewModel.delete_account();
        String response = getString(userInfoViewModel.getmResponseDeleteAccount().getValue());
        crear_snackbar(response, view);
        if (!response.contains("Error:")) {
            goTo(LogInActivity.class);
            finishAffinity();
        }

         */
    }


    public void crear_snackbar(String in, View view){
        Snackbar snackbar = Snackbar.make(view,in,10000);
        snackbar.show();
    }



}