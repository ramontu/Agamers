package dam.agamers.gtidic.udl.agamers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import dam.agamers.gtidic.udl.agamers.views.xats.XatsFragment;

public class allxatsfragment extends Fragment {

    private AllXatsViewModel mViewModel;

    private DatabaseReference mDatabase;

    Button button;

    //LAYOUT
    LinearLayoutManager manager;

    //BINDING
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AllXatsViewModel.class);




        return inflater.inflate(R.layout.all_xats_fragment, container, false);
    }


    public void onCreate(){

        //DESCARREGO TOTS ELS XATS
        //TODO descarregar tots els ids dels xats de l'usuari

        button = (Button) getView().findViewById(R.id.button6);


        mDatabase = FirebaseDatabase.getInstance().getReference();


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Enviat missatge");
                mDatabase.child("Name").setValue("Ramon");
            }
        });


        /*
        DatabaseReference
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

         */



        /*
        //TUTORIAL
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //PROVA
        Message a = new Message();
        a.setText("hola prova des de android");
        a.setName("Ramon Trilla Urtreaga");
        a.setMessageTime();
        a.setPhotoUrl(null);
        a.setImageUrl(null);
        a.setId("1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                myRef.child("messages").push().setValue(a);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                System.out.println("ERROR");
            }
        });
        //myRef.child("users").child("userId").child("username").setValue("name");
        //FI TUTORIAL


        //Inicialitza Realtime Database
        //db = FirebaseDatabase.getInstance();
        //Obtenim la referencia
        //DatabaseReference messageRef = db.getReference().child("messages");



        /*
        //Creem un FirebaseRecyclerAdapter
        FirebaseRecyclerOptions<Message> options = new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(messageRef,Message.class)
                .build();


        adapter = new MessageAdapter(options, "Current name prova"); //TODO canviar per l'username
        manager = new LinearLayoutManager(getActivity());
        manager.setStackFromEnd(true);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


         */
        //Fer autoscroll down quan arribi un nou missatge
        /*
        adapter.registerAdapterDataObserver(
                new ScrollToBottom(getActivity().findViewById(R.id.recyclerView),adapter,manager) // TODO passar la recycle view de la interfaç
        );

         */




        /*
        FirebaseApp abc = db.getApp();
        DatabaseReference abcd = db.getReference();
        //db.getReference();
        //db.getReference().push().setValue(a);



        //***********************************************************************************

        /*
        //proves anteriors
        Message a = new Message();
        a.setText("hola prova des de android");
        a.setName("Ramon Trilla Urtreaga");
        a.setMessageTime();
        a.setPhotoUrl(null);
        a.setImageUrl(null);
        a.setId("1");



         */

        /*
        System.out.println("Obtenint instance");

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        System.out.println("Obtenint reference");
        DatabaseReference data_reference = mDatabase.getReference("Missatges");

         */
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