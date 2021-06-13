package dam.agamers.gtidic.udl.agamers.views.xats;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Message;

public class InxatViewModel extends ViewModel {


    private MutableLiveData<List<Message>> messageListUpdated;

    private List<Message> messageList;

    List<DataSnapshot> messages_snapshot = new ArrayList<>();


    public InxatViewModel(){
        messageList = new ArrayList<>();
    }


    public void load_all_messages(DataSnapshot dataSnapshot){

        dataSnapshot.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                messages_snapshot = new ArrayList<>();
                for (DataSnapshot message : snapshot.child("Messages").getChildren()) {
                    messages_snapshot.add(message);
                }
                poces_chats();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public LiveData<List<Message>> getMessage(DataSnapshot dataSnapshot){
        if (messageListUpdated == null){
            messageListUpdated = new MutableLiveData<>();
            load_all_messages(dataSnapshot);
        }
        return messageListUpdated;
    }

    private void poces_chats() {
        messageList = new ArrayList<>();
        for (DataSnapshot a : messages_snapshot) {
            Message m = new Message();
            //Assignem sendername
            m.setSenderName((String) a.child("senderName").getValue());
            //Assignem temps
            //TODO caldria afegir la diferencia horaria si hi ha
            m.setMessageTime((String) a.child("messageTime").getValue());
            //Assignem text si en te
            if (a.child("text").exists()){
                m.setText((String) a.child("text").getValue());
            }
            //Assignem imatge si hem enviat una imatge
            if (a.child("imageUrl").exists()){
                m.setImageUrl((String) a.child("imageUrl").getValue());
            }
            messageList.add(m);
        }
        messageListUpdated.setValue(messageList);
    }
}