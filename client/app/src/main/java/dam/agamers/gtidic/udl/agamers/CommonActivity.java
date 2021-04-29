package dam.agamers.gtidic.udl.agamers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class CommonActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void goTo(Class _class){
        Log.d(TAG, "goTo() -> Navigate to " + _class.getSimpleName());
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }

    public void showInfoUser(View view, String string){
        Snackbar snackbar;
        snackbar = Snackbar.make(view, string,5000);
        snackbar.show();
    }

}