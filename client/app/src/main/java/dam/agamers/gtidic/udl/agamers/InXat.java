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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import dam.agamers.gtidic.udl.agamers.adapters.MessageAdapter2;

public class InXat extends Fragment {

    private InxatViewModel mViewModel;
    DataSnapshot chat_snapshot;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    private MessageAdapter2 messageadapter;
    private InxatViewModel minXatViewModel;
    private View root;


    public InXat(DataSnapshot chat_snapshot){
        this.chat_snapshot = chat_snapshot;
    }

    public InXat(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //View Model
        minXatViewModel = new ViewModelProvider(this).get(InxatViewModel.class);

        //Desactivem barra inferior
        getActivity().findViewById(R.id.bottom_nav).setVisibility(View.INVISIBLE);

        //Asi
        root = inflater.inflate(R.layout.inxat_fragment, container, false);
        recyclerView = root.findViewById(R.id.inxat_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        messageadapter = new MessageAdapter2(getContext());
        recyclerView.setAdapter(messageadapter);


        Bundle b = getArguments();
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



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InxatViewModel.class);
        // TODO: Use the ViewModel

    }
}