package dam.agamers.gtidic.udl.agamers.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.AbsListView;

import java.util.List;


import dam.agamers.gtidic.udl.agamers.R;
import dam.agamers.gtidic.udl.agamers.adapters.EventAdapter;
import dam.agamers.gtidic.udl.agamers.adapters.EventDiffCallback;
import dam.agamers.gtidic.udl.agamers.viewmodels.ListViewModel;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ListViewModel listViewModel;
    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.eventlist);

        recyclerView.setLayoutManager(new LinearLayoutManager (this));

        eventAdapter = new EventAdapter(new EventDiffCallback());
        recyclerView.setAdapter (eventAdapter);

        initView();
    }

    public void initView () {
        listViewModel = new ListViewModel();
        listViewModel.getEvents();
        listViewModel.returnEvents().observe(this, events -> {
            eventAdapter.submitList(events);
            listViewModel.getEvents();
        });
    }
}