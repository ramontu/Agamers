package dam.agamers.gtidic.udl.agamers;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllxatmessagesViewModel extends ViewModel {
    public void descarregar_conversació(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("message");
        myref.setValue("HOLA PROVA MYREF");
    }
}