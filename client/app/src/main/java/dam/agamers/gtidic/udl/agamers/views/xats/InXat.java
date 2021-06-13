package dam.agamers.gtidic.udl.agamers.views.xats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter;
import dam.agamers.gtidic.udl.agamers.models.Message;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;

public class InXat extends Fragment {

    DataSnapshot chat_snapshot;
    RecyclerView recyclerView;
    private MessageAdapter messageadapter;
    private InxatViewModel minXatViewModel;
    MutableLiveData<List<Message>> messagesListUpdated;


    public InXat(){
        messagesListUpdated = new MutableLiveData<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //View Model
        minXatViewModel = new ViewModelProvider(this).get(InxatViewModel.class);

        //Desactivem barra inferior
        requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);

        Bundle b = getArguments();
        assert b != null;

        //assignem el nom
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("  "+b.getCharSequence("name"));
        toolbar.setLogo(R.drawable.ic_account_circle_black_24dp);


        //Inflem layout
        View root = inflater.inflate(R.layout.inxat_fragment, container, false);
        recyclerView = root.findViewById(R.id.inxat_recycleview);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lm.setStackFromEnd(true);
        recyclerView.setLayoutManager(lm);
        messageadapter = new MessageAdapter(getContext());


        //Ontenim el datasnapshot del chat
        FirebaseDatabase
                .getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference().child("Chats").child(b.getString("ref")).get()
                .addOnSuccessListener(dataSnapshot -> {
                    chat_snapshot = dataSnapshot;
                    System.out.println(b.getString("ref"));
                    System.out.println(chat_snapshot.toString());
                    InitView();
                })
                .addOnFailureListener(e -> {

                });

        return root;
    }


    public void InitView(){
        minXatViewModel.getMessage(chat_snapshot).observe(getViewLifecycleOwner(), message -> {
            System.out.println("Descarregant missatges");
            messageadapter.addMessages(message);
            recyclerView.setAdapter(messageadapter);
            System.out.println("DONE");
        });

        requireActivity().findViewById(R.id.sendButton2).setOnClickListener(v -> {
            Message a = new Message();
            //Assigenem temps d'enviament
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            a.setMessageTime(dtf.format(now));
            //Assignem senderName
            a.setSenderName(PreferencesProvider.providePreferences().getString("username","error"));
            //Assignem text i buidem el edittext
            EditText messagetext = requireActivity().findViewById(R.id.messageEditText2);
            a.setText(messagetext.getText().toString());
            messagetext.setText("");

            //Ho enviem a la firebase
            chat_snapshot.child("Messages").getRef().push().setValue(a);
        });
    }




}