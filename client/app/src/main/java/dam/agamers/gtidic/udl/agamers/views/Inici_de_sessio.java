package dam.agamers.gtidic.udl.agamers.views;

import android.os.Bundle;

import dam.agamers.gtidic.udl.agamers.CommonActivity;
import dam.agamers.gtidic.udl.agamers.R;

public class Inici_de_sessio extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici_de_sessio);
       // Eliminar no us cal... getSupportActionBar().hide();
    }


/* Eliminar
    public void obre_registre (View view){
        Intent i = new Intent(Inici_de_sessio.this, Registre_1.class);
        startActivity(i);
        finish(); // el finish no cal en aquesta navegació
    }
 */
}