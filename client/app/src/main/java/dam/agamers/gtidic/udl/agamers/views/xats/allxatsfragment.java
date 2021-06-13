package dam.agamers.gtidic.udl.agamers.views.xats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.adapters.ChatAdapter;

import dam.agamers.gtidic.udl.agamers.models.Account;
import dam.agamers.gtidic.udl.agamers.models.Chat;
import dam.agamers.gtidic.udl.agamers.preferences.PreferencesProvider;
import dam.agamers.gtidic.udl.agamers.repositories.AccountRepo;

public class allxatsfragment extends Fragment {

    private AllXatsViewModel mViewModel;

    private DatabaseReference mDatabase;

    private RecyclerView recyclerView;
    private View root;

    private ChatAdapter chatAdapter;

    //LAYOUT
    LinearLayoutManager manager;

    //BINDING
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AllXatsViewModel.class);
        //Activem la barra inferior i treiem la icona
        getActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setLogo(null);

        if (!PreferencesProvider.providePreferences().contains("id")){
            System.out.println("Obtenint accountinfo");
            AccountRepo a = new AccountRepo();
            MutableLiveData<Account> b = a.getmAccountInfo();
            b.observe(getViewLifecycleOwner(), new Observer<Account>() {
                @Override
                public void onChanged(Account account) {
                    PreferencesProvider.providePreferences().edit().putInt("id", account.getId()).commit();
                }
            });

        }

        root = inflater.inflate(R.layout.all_xats_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        
        chatAdapter = new ChatAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(chatAdapter);

        initView();
        return root;
    }


    public void initView(){

        mDatabase = FirebaseDatabase.getInstance("https://agamers-49311-default-rtdb.europe-west1.firebasedatabase.app/").getReference(); //FER axo a tot arreu
        mViewModel.getChats().observe(getViewLifecycleOwner(), new Observer<List<Chat>>() {
            @Override
            public void onChanged(List<Chat> chats) {
                System.out.println("Descarregant chats");
                chatAdapter.addChats(chats);
                recyclerView.setAdapter(chatAdapter);
            }
        });

        chatAdapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Chat chat) {
                Bundle b = new Bundle();
                b.putString("ref", chat.getSelf().getRef().getKey());
                b.putString("name", chat.getName());
                NavHostFragment.findNavController(allxatsfragment.this)
                        .navigate(R.id.action_navegacio_xats_to_inxat, b);

                System.out.println(chat.getSelf().getRef().toString());
            }
        });

    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ONcreate");
    }

/*
    public void onCreate(){





        //TODO BUG
        /*
            D/libc-netbsd: getaddrinfo: get result from proxy gai_error = 0
            W/System: ClassLoader referenced unknown path: system/framework/mediatek-cta.jar
            I/System.out: e:java.lang.ClassNotFoundException: com.mediatek.cta.CtaHttp
        /*
        
         */
        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();



        /*

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Enviat missatge");
                mDatabase.child("Name").setValue("Ramon");
            }
        });

         */

        /*
        Message a = new Message();
        a.setText("hola prova des de android");
        a.setName("Ramon Trilla Urtreaga");
        a.setMessageTime();
        a.setPhotoUrl(null);
        a.setImageUrl(null);
        a.setId("1");


        mDatabase.child("messages").child(a.getId()).setValue(a);


         */
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



        //re
        //FI de la prova
    }
    */

}