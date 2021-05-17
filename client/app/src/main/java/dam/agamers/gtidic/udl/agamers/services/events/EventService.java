package dam.agamers.gtidic.udl.agamers.services.events;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Event;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {


    @GET("events")
    Call<List<Event>> getEvents();
}
