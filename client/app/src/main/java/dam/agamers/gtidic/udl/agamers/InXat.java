package dam.agamers.gtidic.udl.agamers;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter2;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.models.Message2;

public class InXat extends Fragment {

    DataSnapshot chat_snapshot;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    private MessageAdapter2 messageadapter;
    private InxatViewModel minXatViewModel;
    private View root;
    MutableLiveData<List<Message2>> messagesListUpdated;


    public InXat(){
        messagesListUpdated = new MutableLiveData<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle b = getArguments();
        //View Model
        minXatViewModel = new ViewModelProvider(this).get(InxatViewModel.class);

        //Desactivem barra inferior
        getActivity().findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);

        //Asi
        root = inflater.inflate(R.layout.inxat_fragment, container, false);
        recyclerView = root.findViewById(R.id.inxat_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        messageadapter = new MessageAdapter2(getContext(), new ArrayList<>());
        recyclerView.setAdapter(messageadapter);



        if (b != null){
            //Ontenim el datasnapshot del chat
            FirebaseDatabase
                    .getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference().child("Chats").child(b.getString("ref")).get()
                    .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            chat_snapshot = dataSnapshot;
                            System.out.println(b.getString("ref"));
                            System.out.println(chat_snapshot.toString());
                            InitView();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {

                        }
                    });
            //Assignem el nom del fragment
            //TODO
        }
        return root;
    }


    public void InitView(){

        minXatViewModel.getMessage(chat_snapshot).observe(getViewLifecycleOwner(), new Observer<List<Message2>>() {
            @Override
            public void onChanged(List<Message2> message) {
                System.out.println("Descarregant missatges");
                messageadapter.addMessages(message);
                recyclerView.setAdapter(messageadapter);
                System.out.println("DONE");
            }
        });
    }

    /*
    private void processar_chat(List<DataSnapshot> in){
        List<Message2> llista = new ArrayList<>();

        for (DataSnapshot a: in){
            Message2 prov = new Message2();
            prov.setMessageTime("messageTime");
            prov.setSendername((String) a.child("senderName").getValue());
            if (a.child("text").exists()){
                prov.setText((String) a.child("text").getValue());
            }
            if (a.child("imageUrl").exists()){
                prov.setImageUrl((String) a.child("imageUrl").getValue());
            }
            System.out.println(prov.toString());
            llista.add(prov);

        }
        messagesListUpdated.setValue(llista);
    }


    private void updatemessage(){
        messagesListUpdated.observe(getViewLifecycleOwner(), new Observer<List<Message2>>() {
            @Override
            public void onChanged(List<Message2> message2s) {
                messageadapter.addMessages(message2s);
                recyclerView.setAdapter(messageadapter);
            }
        });
    }

     */
}