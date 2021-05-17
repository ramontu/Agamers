package dam.agamers.gtidic.udl.agamers.services.events;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Event;
import dam.agamers.gtidic.udl.agamers.network.RetrofitClientInstance;
import dam.agamers.gtidic.udl.agamers.services.events.EventService;
import retrofit2.Call;
import retrofit2.Retrofit;

public class EventServiceImpl implements EventService {

        private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();



        @Override

        public Call<List<Event>> getEvents() {

            return  retrofit.create(EventService.class).getEvents();

        }

}

