package dam.agamers.gtidic.udl.agamers.viewmodels;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.agamers.gtidic.udl.agamers.models.Event;
import dam.agamers.gtidic.udl.agamers.repositories.EventsRepo;

public class ListViewModel extends ViewModel {

    private EventsRepo eventsRepo;

    public ListViewModel () {
        eventsRepo = new EventsRepo();
    }

    public void getEvents() {
        eventsRepo.getEvents();
    }

    public MutableLiveData<List<Event>> returnEvents() {
        return this.eventsRepo.getmResponseEvents();
    }

}
