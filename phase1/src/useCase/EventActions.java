package useCase;
import java.util.HashMap;
import java.util.List;

import entities.*;

public class EventActions {
    public HashMap<String, Event> events; // public private

    // hashmap room key and time as the value
    public HashMap<String, List<Integer>> timeSchedule; // roomID: startHour
    public HashMap<String, List<Integer>> speakerSchedule; // SpeakerID: startHour TODO: decide format
    public HashMap<String, List<String>> attendees; // EventID: attendees TODO: key is event ID, value is a list of attendees who attend the event
    private GenerateID generate = new GenerateID();

    // TODO: Add new event object to the list of all events
    public boolean createEvent(String title, String speakerId, int startHour,
                               List<String> attendees, String roomID){
        String newID = generate.generateId();
        Event newEvent = new Event(newID, title, speakerId, startHour, attendees, roomID);
        // check to make time and room isnt the same
        // no overlap of events
        // if (isRoomFree(roomID, startHour))

        return true;

    }
    // TODO: Create Loader function
    public void loadEvent(String eventID, String title, String speakerId, int startHour,
                          List<String> attendees, String roomID){
        Event newEvent = new Event(eventID, title, speakerId, startHour, attendees, roomID);
        events.put(eventID, newEvent);

    }


    public boolean addAttendee(String eventID, String attendeeID){
        List<String> eventAttendees = attendees.get(eventID);
        if (eventAttendees.contains(attendeeID)) {
            return false;
        } else{
            eventAttendees.add(attendeeID);
            attendees.replace(eventID, eventAttendees);
            return true;
        }
    }

    public boolean removeAttendee(String eventID, String attendeeID){
        List<String> eventAttendees = attendees.get(eventID);
        if (!eventAttendees.contains(attendeeID)) {
            return false;
        } else{
            eventAttendees.remove(attendeeID);
            attendees.replace(eventID, eventAttendees);
            return true;
        }
    }


    // TODO: Change timing of chosen event


    private boolean isRoomFree(Room roomID, int time){
        List<Integer> roomTime = timeSchedule.get(roomID);

        if (roomTime.contains(time)) {
            return false;
        }
        return true;


    }

    private boolean isSpeakerFree(String speakerID, int time){
        List<Integer> SpeakerTime = speakerSchedule.get(speakerID);

        if (SpeakerTime.contains(time)) {
            return false;
        }
        return true;

    }

    // TODO: How do exceptions work?
    public void eventAttendees(String eventID) {
        try {
            System.out.println(attendees.get(eventID));
        } catch (Exception eventInvaild) {
            System.out.println("This event does not exist");

        }
    }
}
