package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dam.agamers.gtidic.udl.agamers.R;

public class Inici_de_sessio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici_de_sessio);
        getSupportActionBar().hide();
    }



    public void obre_registre (View view){
        Intent i = new Intent(Inici_de_sessio.this, Registre_1.class);
        startActivity(i);
        finish();
    }
}