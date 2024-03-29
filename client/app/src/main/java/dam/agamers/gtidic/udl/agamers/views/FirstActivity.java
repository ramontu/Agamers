package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.databinding.NavHeaderMainBinding;
import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.LogInActivity;
import dam.agamers.gtidic.udl.agamers.views.activitatsuser.UserInfoActivity;

public class FirstActivity extends CommonActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = findViewById(R.id.toolbar); //això és perquè utilitzo la meva toolbar costumitzada
        setSupportActionBar(toolbar);

        //Navegació inferior "bottom navigation"
        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navegacio_xats, R.id.navegacio_match, R.id.navegacio_forums, R.id.nav_inici, R.id.nav_notificacions, R.id.nav_peticions, R.id.nav_favorits, R.id.nav_jocs, R.id.nav_botiga, R.id.nav_tornejos, R.id.nav_compte, R.id.nav_configuracio, R.id.nav_tancarsessio)
                .build();
        NavController bottomNavigationController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, bottomNavigationController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavView, bottomNavigationController);

        //Navegació lateral "drawer navigation"

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView leftNavView = findViewById(R.id.left_nav);
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.nav_header_main, leftNavView, true);
        navHeaderMainBinding.setAccount(new Account());
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navegacio_xats, R.id.navegacio_match, R.id.navegacio_forums, R.id.nav_inici, R.id.nav_notificacions, R.id.nav_peticions, R.id.nav_favorits, R.id.nav_jocs, R.id.nav_botiga, R.id.nav_tornejos, R.id.nav_compte, R.id.nav_configuracio, R.id.nav_tancarsessio)
                .setDrawerLayout(drawer)
                .build();
        NavController leftNavigationController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, leftNavigationController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(leftNavView, leftNavigationController);
    }

    public void onBackPressed(){
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


}