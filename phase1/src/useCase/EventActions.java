package useCase;
import java.util.HashMap;
import java.util.List;

import entities.*;
import useCase.GenerateID;

public class EventActions {
    public HashMap<String, List<Event>> events; // public private
    // hashmap room key and time as the value
    private GenerateID generate = new GenerateID();

    // TODO: Add new event object to the list of all events
    public boolean createEvent(String title, String speaker, int startHour,
                               List<String> attendees, String location){
        String newID = generate.generateId();
        Event newEvent = new Event(newID, title, speaker, startHour, attendees, location);
        // check to make time and room isnt the same
        // no overlap of events
        return true;

    };

    // TODO: Add users to attendee list of chosen event
    public boolean addAttendee(String attendeeID){
        return false;
    };
    // TODO: Remove users from attendee list of chosen event
    // TODO: Change timing of chosen event
    // TODO: Check if time is free before changing time

    // TODO: Return list of attendees of an event

}
