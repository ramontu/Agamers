package dam.agamers.gtidic.udl.agamers;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;
import dam.agamers.gtidic.udl.agamers.repositories.ChatRepo;


public class AllXatsViewModel extends ViewModel {
    private MutableLiveData<Boolean> singinOK = new MutableLiveData<Boolean>(false);
    private AtomicInteger vegades_intentat = new AtomicInteger(0);

    private Integer open_chats = 0;

    private AccountRepo accountRepo;
    private ChatRepo chatRepo;

    private MutableLiveData<List<Chat>> chatListUpdated;

    private List<Chat> chatList;

    private DatabaseReference databaseReference;

    public MutableLiveData<Account> account;


    public AllXatsViewModel(){
        accountRepo = new AccountRepo();
        chatRepo = new ChatRepo();
        databaseReference = FirebaseDatabase.getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        chatList = new ArrayList<Chat>();
    }

    private void loadmessages(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                chatList = new ArrayList<>();
                List<DataSnapshot> messages_snapshot = new ArrayList<>();

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    for (DataSnapshot chat : snapshot1.getChildren()){
                        //TODO abans de fer res comprovar que el teu id esta a participants


                        Double id = (double) account.getValue().getId();
                        boolean id_ok = false;

                        for (DataSnapshot a : chat.child("Participants").getChildren()){
                            //TODO els obte ok
                            System.out.println("Ids"+a.getValue());
                            if (a.getValue() == id){
                                id_ok = true;
                                System.out.println("He trobat un xat meu");
                            }
                        }

                        if (id_ok){
                            messages_snapshot.add(chat);
                        }


                        Chat m = new Chat();

                        //Assignació del nom del xat, si en te significa que es una crew,
                        //sino es tracta de un xat entre 2 usuaris
                        if (chat.child("Name").exists()){
                            m.setName((String) chat.child("Name").getValue());

                            //Assignació de foto si en te
                            if (chat.child("ImageUrl").exists()){
                                m.setImageUrl((String) chat.child("ImageUrl").getValue());
                            }
                        }
                        else {
                            //TODO descarregar ids dels participants de firebase
                            //TODO mirar quin dels 2 ids correspon a l'actual i quin a l'altre
                            //TODO descarregar els username i foto de l'altre
                            //Assignar el nom del xat i la photo de l'altre usuari
                        }




                        for (DataSnapshot messages : chat.child("Messages").getChildren()){
                            Message me = new Message();
                            me.setName((String) messages.child("senderName").getValue());
                            if (messages.child("text").exists()){
                                me.setText((String) messages.child("text").getValue());
                                //TODO debug
                                System.out.println(me.getText());
                            }

                            m.getMessages().add(me);
                        }

                        chatList.add(m);
                    }
                }
                chatListUpdated.setValue(chatList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public LiveData<List<Chat>> getChats(){
        if (chatListUpdated == null){
            chatListUpdated = new MutableLiveData<List<Chat>>();
            loadmessages();
        }
        return chatListUpdated;
    }

    /*
    public LiveData<List<Chat>> getMessage(){
        if (chatListUpdated == null){
            chatListUpdated = new MutableLiveData<List<Chat>>();
            loadmessages();
        }
        return chatListUpdated;
    }

     */


}