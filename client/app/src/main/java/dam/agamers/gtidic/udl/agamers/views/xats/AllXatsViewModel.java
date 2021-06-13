package dam.agamers.gtidic.udl.agamers.views.xats;

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
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
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

    List<DataSnapshot> messages_snapshot = new ArrayList<>();

    private int id;

    public AllXatsViewModel(){
        accountRepo = new AccountRepo();
        chatRepo = new ChatRepo();
        databaseReference = FirebaseDatabase.getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        chatList = new ArrayList<Chat>();
    }

    //FUNCIONA
    private void load_my_chats(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                messages_snapshot = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    for (DataSnapshot chat : snapshot1.getChildren()) {

                        //Obtenim l'id de l'usuari per a que es pugui comprovar si esta en aquell xat o no
                        id =  PreferencesProvider.providePreferences().getInt("id", 0);
                        boolean id_ok = false;

                        for (DataSnapshot a : chat.child("Participants").child("ids_name").getChildren()) {
                            if (a.getKey().toString().equals(String.valueOf(id))) {
                                id_ok = true;
                            }
                        }

                        if (id_ok) {
                            messages_snapshot.add(chat);
                        }
                    }
                }
                poces_chats();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public LiveData<List<Chat>> getChats(){
        if (chatListUpdated == null){
            chatListUpdated = new MutableLiveData<List<Chat>>();
            load_my_chats();
        }
        return chatListUpdated;
    }

    private void poces_chats() {
        chatList = new ArrayList<>();


        for (DataSnapshot a : messages_snapshot) {
            Chat m = new Chat();
            //Assignació del nom del xat, si en te significa que es una crew,
            //sino es tracta de un xat entre 2 usuaris

            if (a.child("Name").exists()){
                m.setName((String) a.child("Name").getValue());

                //Assignació de foto si en te
                if (a.child("ImageUrl").exists()){
                    m.setImageUrl((String) a.child("ImageUrl").getValue());
                }
            }
            else {
                for (DataSnapshot participants : a.child("Participants").child("ids_name").getChildren()){
                    if (!participants.getKey().equals(String.valueOf(id))){
                        m.setName((String) participants.getValue());
                        //TODO descarregar la imatge des de publicprofile i assignar-la com a imatge

                    }
                    else {
                        PreferencesProvider.providePreferences().edit().putString("username", (String) participants.getValue()).commit();
                    }
                }
            }

            //Assignem el datasnapshot dels missatges
            m.setMessages(a.child("Messages"));

            //Assignem el datasnapshot del chat sencer
            m.setSelf(a);

            /* OUTDATED
            for (DataSnapshot message : a.child("Messages").getChildren()){
                Message new_message = new Message();
                new_message.setText((String) message.child("text").getValue());
                m.getMessages().add(new_message);
            }

             */

            //Obtenim els participants del xat
            for (DataSnapshot participant : a.child("Participants").child("ids_name").getChildren()){
                String [] participants = new String[2];
                participants[0] = participant.getKey();
                participants[1] = (String) participant.getValue();
                m.getParticipants().add(participants);
            }

            /*
        for (DataSnapshot messages : chat.child("Messages").getChildren()){
            Message me = new Message();
            me.setName((String) messages.child("senderName").getValue());
            if (messages.child("text").exists()){
                me.setText((String) messages.child("text").getValue());
                //TODO debug
                System.out.println(me.getText());
            }

            m.getMessages().add(me);


             */
                chatList.add(m);
        }
        chatListUpdated.setValue(chatList);

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


}