package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;

public class FirstActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    

    public void obrir_info_user(View view) {
        goTo(UserInfoActivity.class);
    }
}