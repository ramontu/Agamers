package dam.agamers.gtidic.udl.agamers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

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
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter2;
import dam.agamers.gtidic.udl.agamers.models.Message2;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class InXat extends Fragment {

    DataSnapshot chat_snapshot;
    RecyclerView recyclerView;
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
        //View Model
        minXatViewModel = new ViewModelProvider(this).get(InxatViewModel.class);

        //Desactivem barra inferior
        getActivity().findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);

        Bundle b = getArguments();
        assert b != null;

        //assignem el nom
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("  "+b.getCharSequence("name"));
        //toolbar.setNavigationIcon(R.drawable.ic_account_circle_black_36dp);
        toolbar.setLogo(R.drawable.ic_account_circle_black_24dp);
        //toolbar.setCollapseIcon(R.drawable.ic_account_circle_black_36dp); //TODO canviar per la del usuari


        //Inflem layout
        root = inflater.inflate(R.layout.inxat_fragment, container, false);
        recyclerView = root.findViewById(R.id.inxat_recycleview);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lm.setStackFromEnd(true);
        recyclerView.setLayoutManager(lm);
        messageadapter = new MessageAdapter2(getContext());


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

        return root;
    }


    public void InitView(){
        minXatViewModel.getMessage(chat_snapshot).observe(getViewLifecycleOwner(), new Observer<List<Message2>>() {
            @Override
            public void onChanged(List<Message2> message) {
                System.out.println("Descarregant missatges");
                messageadapter.addMessages(message);
                recyclerView.setAdapter(messageadapter);
                //recyclerView.smoothScrollToPosition(message.size()); //OK
                System.out.println("DONE");
            }
        });

        getActivity().findViewById(R.id.sendButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message2 a = new Message2();
                //Assigenem temps d'enviament
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                a.setMessageTime(dtf.format(now));
                //Assignem senderName
                a.setSenderName(PreferencesProvider.providePreferences().getString("username","error"));
                //Assignem text i buidem el edittext
                EditText messagetext = getActivity().findViewById(R.id.messageEditText2);
                a.setText(messagetext.getText().toString());
                messagetext.setText("");

                chat_snapshot.child("Messages").getRef().push().setValue(a);
            }
        });
    }




}