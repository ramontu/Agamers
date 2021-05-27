package dam.agamers.gtidic.udl.agamers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter;
import dam.agamers.gtidic.udl.agamers.models.Message;

public class allxatsfragment extends Fragment {

    private AllXatsViewModel mViewModel;


    //FIREBASE
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private MessageAdapter adapter;


    //LAYOUT
    LinearLayoutManager manager;

    //BINDING
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.all_xats_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllXatsViewModel.class);


        //Iniciar sessió a la bbdd si no s'ha registrat cap usuari abans
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null){
            auth = mViewModel.signIn_Firebase();
        }



        //Inicialitza Realtime Database
        db = FirebaseDatabase.getInstance();
        //Obtenim la referencia
        DatabaseReference messageRef = db.getReference().child("messages");


        //Creem un FirebaseRecyclerAdapter
        FirebaseRecyclerOptions<Message> options = new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(messageRef,Message.class)
                .build();


        adapter = new MessageAdapter(options, "Current name prova"); //TODO canviar per l'username
        manager = new LinearLayoutManager(getActivity());
        manager.setStackFromEnd(true);
        RecyclerView recyclerView = getView().findViewById(R.id.messageRecyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        //proves anteriors
        Message a = new Message();
        a.setText("hola prova des de android");
        a.setName("Ramon Trilla Urtreaga");
        a.setMessageTime();
        a.setPhotoUrl(null);
        a.setImageUrl(null);
        a.setId("1");



        System.out.println("Obtenint instance");

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        System.out.println("Obtenint reference");
        DatabaseReference data_reference = mDatabase.getReference("Missatges");

        /*
        FirebaseRecyclerOptions<Message> options = new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(ref_fill, Message.class)
                .build();


         */
        /*
        adapter = new MessageAdapter();
        //adapter.options(options,auth.getUid());
        //adapter.options(new MessageAdapter().options(new Message(),"hola"),auth.getUid());
        Message mes = new Message("1","prova prova","hello soy prova","","","ara");
        db.getReference().child(Objects.requireNonNull(auth.getUid())).push().setValue(a);

         */

        //re
        //FI de la prova
    }
}