package dam.agamers.gtidic.udl.agamers.views;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter;
import dam.agamers.gtidic.udl.agamers.databinding.NavHeaderMainBinding;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.UserInfoActivity;

public class FirstActivity extends CommonActivity {

    private static final int PICK_IMAGE_REQUEST = 14;
    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private MessageAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = findViewById(R.id.toolbar); //això és perquè utilitzo la meva toolbar costumitzada
        setSupportActionBar(toolbar);

        //Navegació inferior "bottom navigation"
        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navegacio_xats, R.id.navegacio_match, R.id.navegacio_forums, R.id.nav_inici, R.id.nav_notificacions, R.id.nav_peticions, R.id.nav_favorits, R.id.nav_jocs, R.id.nav_botiga, R.id.nav_tornejos, R.id.nav_compte, R.id.nav_configuracio, R.id.nav_tancarsessio, R.id.fragmentaddgame)
                .build();
        NavController bottomNavigationController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, bottomNavigationController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavView, bottomNavigationController);

        //Navegació lateral "drawer navigation"

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView leftNavView = findViewById(R.id.left_nav);
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_main, leftNavView, true);
        navHeaderMainBinding.setAccount(new Account());
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navegacio_xats, R.id.navegacio_match, R.id.navegacio_forums, R.id.nav_inici, R.id.nav_notificacions, R.id.nav_peticions, R.id.nav_favorits, R.id.nav_jocs, R.id.nav_botiga, R.id.nav_tornejos, R.id.nav_compte, R.id.nav_configuracio, R.id.nav_tancarsessio, R.id.fragmentaddgame)
                .setDrawerLayout(drawer)
                .build();
        NavController leftNavigationController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, leftNavigationController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(leftNavView, leftNavigationController);



        //FIREBASE
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        singInFirebase();


    }

    public void onBackPressed() {
        goTo(LogInActivity.class);
    }

    public void obrir_info_user(View view) {
        goTo(UserInfoActivity.class);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void close_session(View view) {

        AccountRepo accountRepo = new AccountRepo();
        accountRepo.deleteToken();
        if (accountRepo.getmDeleteTokenOk().getValue()) {
            goTo(LogInActivity.class);
            finish();
        }
        showInfoUser(getCurrentFocus(), "Error al tancar la sesió");
    }

    public void checkExternalStoragePermission_addgame(View view) {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        pick();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    public void pick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), PICK_IMAGE_REQUEST);
    }


    public void singInFirebase() {



        /*
        auth.signInWithCustomToken("USER1").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                System.err.println(task.isSuccessful());
            }
        });

         */


        //CORRECTE
        if (auth.getCurrentUser() == null) {
            auth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        System.out.println("Create User OK");
                        //Guardar UID (userid a la nostra base de dades per saber a quin fill ens hem de referir
                        //TODO mostrar missatge de que s'ha iniciat sessió correctament

                    } else {
                        System.out.println("Create User OK");
                        //TODO mostrar missatge d'error i possiblement tornar a intentar-ho
                    }
                }
            });
        }

        //PROVES
        /*
        db.getReference().setValue("Users");
        db.getReference().child("Users").setValue("User1");
        db.getReference().child("Users").child("user1").setValue("Hola")
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                System.out.println("Succes");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                System.out.println("Error");
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                System.out.println("Canceled");
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                System.out.println(task.getResult());
            }
        });

         */
    }

}