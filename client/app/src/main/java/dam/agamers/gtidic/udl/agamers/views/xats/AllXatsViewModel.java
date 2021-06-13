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
import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;


public class AllXatsViewModel extends ViewModel {



    private MutableLiveData<List<Chat>> chatListUpdated;

    private List<Chat> chatList;

    private final DatabaseReference databaseReference;

    public MutableLiveData<Account> account;

    List<DataSnapshot> messages_snapshot = new ArrayList<>();

    private int id;

    public AllXatsViewModel(){
        databaseReference = FirebaseDatabase.getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        chatList = new ArrayList<>();
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
                            if (Objects.equals(a.getKey(), String.valueOf(id))) {
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
            chatListUpdated = new MutableLiveData<>();
            load_my_chats();
        }
        return chatListUpdated;
    }

    private void poces_chats() {
        chatList = new ArrayList<>();
        for (DataSnapshot a : messages_snapshot) {
            Chat m = new Chat();
            /*
                Assignació del nom del xat, si en te significa que es una crew,
                sino es tracta de un xat entre 2 usuaris
             */
            if (a.child("Name").exists()){
                m.setName((String) a.child("Name").getValue());

                //Assignació de foto si en te
                if (a.child("ImageUrl").exists()){
                    m.setImageUrl((String) a.child("ImageUrl").getValue());
                }
            }
            else {
                for (DataSnapshot participants : a.child("Participants").child("ids_name").getChildren()){
                    if (!Objects.equals(participants.getKey(), String.valueOf(id))){
                        m.setName((String) participants.getValue());
                        //TODO descarregar la imatge des de publicprofile i assignar-la com a imatge
                    }
                }
            }

            //Assignem el datasnapshot dels missatges
            m.setMessages(a.child("Messages"));

            //Assignem el datasnapshot del chat sencer
            m.setSelf(a);

            //Obtenim els participants del xat
            for (DataSnapshot participant : a.child("Participants").child("ids_name").getChildren()){
                String [] participants = new String[2];
                participants[0] = participant.getKey();
                participants[1] = (String) participant.getValue();
                m.getParticipants().add(participants);
            }
                chatList.add(m);
        }
        chatListUpdated.setValue(chatList);
    }


}