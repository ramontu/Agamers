package dam.agamers.gtidic.udl.agamers;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;


public class AllXatsViewModel extends ViewModel {
    private MutableLiveData<Boolean> singinOK = new MutableLiveData<Boolean>(false);
    private AtomicInteger vegades_intentat = new AtomicInteger(0);
    private FirebaseAuth firebaseAuth = null;

    private Integer open_chats = 0;

    private

    public AllXatsViewModel(){

    }


    public FirebaseAuth signIn_Firebase(){
        //S'ha de fer inici de sessió anonimament sino s'ha de protar tot el servei a firebase
        //Iniciar sessió anonimament i buscar dins de la nostra base de dades el nom del xat
        //fer un child dins de missatges per a cada xat i aixi no haver de guardar cada numero de missatge

        while (!singinOK.getValue() && (vegades_intentat.get() < 10)){
            Log.d("AllXatsViewModel", "Intento connectar-me a la base de dades");

            //Al cap de 500 milisegons crida al metode auth_firebase que torna a intentar-ho
            (new Handler()).postDelayed(this::auth_firebase, 500);
        }
        return firebaseAuth;
    }

    private void auth_firebase(){
        FirebaseAuth.getInstance().signInAnonymously().addOnSuccessListener(authResult -> {
            singinOK.setValue(true);
            firebaseAuth = (FirebaseAuth) authResult;
        }).addOnFailureListener(e -> {
            singinOK.setValue(false);
            e.printStackTrace();
        });
        vegades_intentat.addAndGet(1);
    }
}