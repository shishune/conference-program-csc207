package useCase;
import java.util.HashMap;
import java.util.List;

import entities.*;
import useCase.GenerateID;

public class EventActions {
    public HashMap<String, List<Event>> events; // public private

    // hashmap room key and time as the value
    public HashMap<String, List<Integer>> timeSchedule; // roomID: startHour
    public HashMap<String, List<String>> speakerSchedule; // time-date: speakerID TODO: decide format
    public HashMap<String, List<String>> attendees; // TODO: key is event ID, value is a list of attendees who attend the event
    private GenerateID generate = new GenerateID();

    // TODO: Add new event object to the list of all events
    public boolean createEvent(String title, String speaker, int startHour,
                               List<String> attendees, String roomID){
        String newID = generate.generateId();
        Event newEvent = new Event(newID, title, speaker, startHour, attendees, roomID);
        // check to make time and room isnt the same
        // no overlap of events
        // if (isRoomFree(roomID, startHour))

        return true;

    }


    // TODO: Add users to attendee list of chosen event
    public boolean addAttendee(String attendeeID){
        return false;
    }
    // TODO: Remove users from attendee list of chosen event


    // TODO: Change timing of chosen event

    // TODO: Check if time is free before changing time
    /*private boolean isRoomFree(Room room, int time){

    }*/
    // TODO: Check if speaker is free
    /*private boolean isSpeakerFree(String speakerID){

    }*/

    // TODO: Return list of attendees of an event

}
