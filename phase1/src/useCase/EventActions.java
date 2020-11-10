package useCase;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import entities.*;
import gateway.LoadUp;
import gateway.LoadUpIGateway;

public class EventActions  {
    public HashMap<String, Event> events; // public private

    // hashmap room key and time as the value
    public HashMap<String, List<LocalDateTime>> timeSchedule; // roomID: date
    public HashMap<String, List<LocalDateTime>> speakerSchedule; // SpeakerID: date
    public HashMap<String, List<String>> attendees; // EventID: attendees
    private GenerateID generate = new GenerateID();


    private LoadUp loader = new LoadUp(); // this is okay because IGateway

    /** gets list of events from the IGateway **/
    private void getAllEvents() {
        List<String> eventList = loader.getEventsList();
        for (String event: eventList){
            String[] eventAttributes = event.split(", ");
            List<String> eventAttendees = Arrays.asList(eventAttributes[3].split(" "));
            String dateAttributes = eventAttributes[4];
            // date : 2018-12-25T04:00

//            LocalDateTime dateTime = LocalDateTime.of(dateAttributes.substring(0, 3),
//                    (int) dateAttributes.substring(5, 6));
            LocalDateTime dateTime = LocalDateTime.parse(dateAttributes);

            loadEvent(eventAttributes[0], eventAttributes[1], eventAttributes[2], dateTime,
                    eventAttendees, eventAttributes[5]);
        }
    }

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
