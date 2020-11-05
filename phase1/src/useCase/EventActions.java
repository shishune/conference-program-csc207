package useCase;
import java.util.HashMap;
import java.util.List;

import entities.*;
import useCase.GenerateID;

public class EventActions {
    private HashMap<String, List<Event>> eventsList; // public private
    private GenerateID generate = new GenerateID();

    // TODO: Add new event object to the list of all events
    public void createEvent(String title, String speaker, int startHour,
                               List<String> attendees, String location){
        String newID = generate.generateId();
        Event newEvent = new Event(newID, title, speaker, startHour, attendees, location);


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
