package dam.agamers.gtidic.udl.agamers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Event;
import dam.agamers.gtidic.udl.agamers.services.events.EventService;
import dam.agamers.gtidic.udl.agamers.services.events.EventServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsRepo {
    private final EventService eventService;

    private final MutableLiveData<List<Event>> mResponseEvents;

    public EventsRepo() {
        this.eventService = new EventServiceImpl();
        this.mResponseEvents = new MutableLiveData<>();
    }

    public void getEvents(){
        this.eventService.getEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                int code =response.code();
                        if(code == 200){
                            Log.d("EventsRepo", "Ha rebut el codi 200");
                            List<Event> events = response.body();
                        }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }
}
