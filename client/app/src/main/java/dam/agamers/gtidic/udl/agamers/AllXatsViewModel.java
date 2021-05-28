package dam.agamers.gtidic.udl.agamers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.atomic.AtomicInteger;

import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;


public class AllXatsViewModel extends ViewModel {
    private MutableLiveData<Boolean> singinOK = new MutableLiveData<Boolean>(false);
    private AtomicInteger vegades_intentat = new AtomicInteger(0);

    private Integer open_chats = 0;

    private AccountRepo accountRepo;

    public AllXatsViewModel(){
        accountRepo = new AccountRepo();
    }


    public void signIn_Firebase(){
        //S'ha de fer inici de sessió anonimament sino s'ha de protar tot el servei a firebase
        //Iniciar sessió anonimament i buscar dins de la nostra base de dades el nom del xat


        //TODO al actualitzar la informació del compte també fer-ho de firebase
        //Iniciar sessió amb el correu i la contrasenya si no te un tocken guardat


        //fer un child dins de missatges per a cada xat i aixi no haver de guardar cada numero de missatge


        auth_firebase();

    }

    private void auth_firebase(){

        /*
        //Account user = accountRepo.getmAccountInfo().getValue();
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){  //user.getFirebase_credential().isEmpty()
            firebaseAuth.createUserWithEmailAndPassword("prova@prova.com", "provaprova") //user.getEmail(), user.getPassword()
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                FirebaseUser fire_user = firebaseAuth.getCurrentUser();
                                Log.d("AuthFirebase", fire_user.getUid());
                                
                                //TODO guardar el token a user i actualitx¡zar la info
                            }
                        }
                    });
        }


         */



        //FirebaseUser a = firebaseAuth.getCurrentUser();



        /*
        FirebaseAuth.getInstance().signInWithCustomToken("user.getUsername()") //TODO Canviar pel real
                .addOnSuccessListener(authResult -> {
                    Log.d("Auth_firebase()","Succes");
                    singinOK.setValue(true);
                    this.authResult =  authResult;
                }).addOnFailureListener(e -> {
            Log.d("Auth_firebase()","Failure, Torno a intentar connectar-me a la base de dades");
            singinOK.setValue(false);
            //TODO posar un control per a que no ho pugui fer de manera indefinida
            //Al cap de 1500 milisegons crida al metode auth_firebase que torna a intentar-ho
            (new Handler()).postDelayed(this::auth_firebase, 1500);
            e.printStackTrace();
        });

         */
    }
}