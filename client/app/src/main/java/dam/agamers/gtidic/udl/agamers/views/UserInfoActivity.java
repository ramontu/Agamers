package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.viewmodels.UserInfoViewModel;

public class UserInfoActivity extends CommonActivity {

    UserInfoViewModel userInfoViewModel = new UserInfoViewModel();
    private Button EnviarPet;
    private Button Xatejar;
    private Button Compartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().hide();
        EnviarPet = findViewById(R.id.button3);
        Xatejar = findViewById(R.id.button5);
        Compartir = findViewById(R.id.button6);

        EnviarPet.setOnClickListener(v -> {
            //aquí hauriem d'enviar una petició d'amistat a l'usuari al qual pertany el perfil @usuari_x. Aquesta petició es guardarà en l'apartat de peticions del @usuari_x

        });
        Xatejar.setOnClickListener(v -> {
            //aquí hauriem d'obrir l'apartat de xats per tal de que s'obrís el xat amb l'usuari del perfil @usuari_x. Si anteriorment ja s'havia generat aquest xat el programa ha de carregar-lo, si no haurà de generar-lo

        });
        Compartir.setOnClickListener(v -> {
            //aquí hauriem de compartir el perfil generant un link que portés al perfil d'usuari que volem compartir, fent que aquest es pogués enviar via mail, whatsapp...

        });

    }

    public void open_edit_info(View view){
        goTo(EditAccountActivity.class);
    }

    public void close_session(View view){
        PreferencesProvider.providePreferences().edit().remove("token").apply();
        goTo(LogInActivity.class);
        finishAffinity();
    }

    public void  button3OnClick(View view){

    }

    public void delete_account (View view){
        userInfoViewModel.delete_account();
        String response = getString(userInfoViewModel.getmResponseDeleteAccount().getValue());
        crear_snackbar(response,view);
        if (!response.contains("Error:")){
            goTo(LogInActivity.class);
            finishAffinity();
        }
    }

    public void crear_snackbar(String in, View view){
        Snackbar snackbar = Snackbar.make(view,in,10000);
        snackbar.show();
    }
}