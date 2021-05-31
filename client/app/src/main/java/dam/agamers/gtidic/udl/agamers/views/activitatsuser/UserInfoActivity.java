package dam.agamers.gtidic.udl.agamers.views.activitatsuser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.models.Profile;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.viewmodels.ProfileViewModel;
import dam.agamers.gtidic.udl.agamers.viewmodels.UserInfoViewModel;

public class UserInfoActivity extends CommonActivity {

    ProfileViewModel profileInfoViewModel;

    private Button EnviarPet;
    private Button Xatejar;
    private Button Compartir;

    private TextView usernameTextView;
    private TextView bioTextView;

    private ImageView imatgePerfil;
    UserInfoViewModel userInfoViewModel = new UserInfoViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfilpublic);
        getSupportActionBar().hide();
        profileInfoViewModel = new ProfileViewModel();
        profileInfoViewModel.getProfile();


        profileInfoViewModel.agafarProfile().observe(this, new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                usernameTextView.setText(profile.getUsername());
                bioTextView.setText(profile.getLongdesc());
                Picasso.get().load(profile.getImage()).into(imatgePerfil);
            }
        });

        EnviarPet = findViewById(R.id.boto_solicitud);
        Xatejar = findViewById(R.id.boto_xat);
        Compartir = findViewById(R.id.boto_compartir);
        usernameTextView = findViewById(R.id.usernametxt);
        bioTextView = findViewById(R.id.bio);

        imatgePerfil = findViewById(R.id.profileimage);


        EnviarPet.setOnClickListener(v -> {
            findViewById(R.id.boto_solicitud);
            //aquí hauriem d'enviar una petició d'amistat a l'usuari al qual pertany el perfil @usuari_x. Aquesta petició es guardarà en l'apartat de peticions del @usuari_x

        });
        Xatejar.setOnClickListener(v -> {
            findViewById(R.id.boto_xat);
            //aquí hauriem d'obrir l'apartat de xats per tal de que s'obrís el xat amb l'usuari del perfil @usuari_x. Si anteriorment ja s'havia generat aquest xat el programa ha de carregar-lo, si no haurà de generar-lo

        });
        Compartir.setOnClickListener(v -> {
            findViewById(R.id.boto_compartir);
            //aquí hauriem de compartir el perfil generant un link que portés al perfil d'usuari que volem compartir, fent que aquest es pogués enviar via mail, whatsapp...

        });

    }

    public void open_edit_info(View view){
        goTo(EditAccountActivity.class);
    }


    public void close_session(View view){
        userInfoViewModel.close_session();
        finish();
        goTo(LogInActivity.class);
        finishAffinity();
    }

    public void delete_account (View view) {
        userInfoViewModel.delete_account();
        String response = getString(userInfoViewModel.getmResponseDeleteAccount().getValue());
        crear_snackbar(response, view);
        if (!response.contains("Error:")) {
            goTo(LogInActivity.class);
            finishAffinity();
        }
    }

    public void  button3OnClick(View view){

    }

    public void crear_snackbar(String in, View view){
        Snackbar snackbar = Snackbar.make(view,in,10000);
        snackbar.show();
    }
}