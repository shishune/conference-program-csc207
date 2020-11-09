package useCase;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import entities.*;

public class EventActions {
    public HashMap<String, Event> events; // public private

    // hashmap room key and time as the value
    public HashMap<String, List<LocalDateTime>> timeSchedule; // roomID: date
    public HashMap<String, List<LocalDateTime>> speakerSchedule; // SpeakerID: date
    public HashMap<String, List<String>> attendees; // EventID: attendees
    private GenerateID generate = new GenerateID();


    public boolean createEvent(String title, String speakerId, LocalDateTime dateTime,
                               List<String> attendees, String roomID){
        if (isRoomFree(roomID, dateTime) && isSpeakerFree(speakerId, dateTime)){
            String newID = generate.generateId();
            loadEvent(newID, title, speakerId, dateTime, attendees, roomID);
            return true;
        }
        return false;
    }


    public void loadEvent(String eventID, String title, String speakerId, LocalDateTime dateTime,
                          List<String> attendees, String roomID){
        Event newEvent = new Event(eventID, title, speakerId, dateTime, attendees, roomID);
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



    public boolean changeEventTime(String eventID, LocalDateTime newDateTime){
        if(isRoomFree(events.get(eventID).getRoomID(), newDateTime) &&
                isSpeakerFree(events.get(eventID).getSpeaker(), newDateTime)){
            events.get(eventID).setDateTime(newDateTime);
            return true;
        }
        return false;

    }

    private boolean isRoomFree(String roomID, LocalDateTime dateTime){

        List<LocalDateTime> roomTime = timeSchedule.get(roomID);

        if (roomTime.contains(dateTime)) {
            return false;
        }
        return true;

    }

    private boolean isSpeakerFree(String speakerID, LocalDateTime dateTime){
        List<LocalDateTime> SpeakerTime = speakerSchedule.get(speakerID);

        if (SpeakerTime.contains(dateTime)) {
            return false;
        }
        return true;
    }

    // TODO: if event dne: return empty string inside list
    public List<String> eventAttendees(String eventID){

        return attendees.get(eventID);
    }
}
