package dam.agamers.gtidic.udl.agamers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;


public class AllXatsViewModel extends ViewModel {
    private MutableLiveData<Boolean> singinOK = new MutableLiveData<Boolean>(false);
    private AtomicInteger vegades_intentat = new AtomicInteger(0);

    private Integer open_chats = 0;

    private AccountRepo accountRepo;

    private MutableLiveData<List<Message>> messageListUpdated;

    private List<Message> messageList;

    private DatabaseReference databaseReference;


    public AllXatsViewModel(){
        accountRepo = new AccountRepo();
        databaseReference = FirebaseDatabase.getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/").getReference(); //FER axo a tot arreu
        messageList = new ArrayList<>();
    }

    private void loadmessages(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    for (DataSnapshot message : snapshot1.getChildren()){
                        Message m = new Message();
                        m.setName((String) message.child("name").getValue());
                        messageList.add(m);

                    }
                }
                messageListUpdated.setValue(messageList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public LiveData<List<Message>> getMessage(){
        if (messageListUpdated == null){
            messageListUpdated = new MutableLiveData<>();
            loadmessages();
        }
        return messageListUpdated;
    }


}