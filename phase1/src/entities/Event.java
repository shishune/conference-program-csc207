package entities;
import java.lang.reflect.Array;
import java.util.List;
import java.time.*;

public class Event {
    private String eventID;
    private String title;
    private List<String> speakers;
    private int startHour;
    private List<String> attendees;
    private String location;
    private static int capacity = 2;

    public Event(String title){

    }

    // Stores, setter and getter of title of talk
    //Stores, setter and getter list of speaker(s) for this talk
    //Stores, setter and getter of Organizers in charge of this talk
    //Stores, setter and getter for start time of talk
    //Stores, setter and getter for list of attendees for this talk
    //Store, setter and getter of location/room
    //Store, setter and getter of capacity (for now assume 2)


}