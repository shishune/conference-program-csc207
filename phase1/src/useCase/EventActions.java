package useCase;
import java.util.*;

import entities.*;
import gateway.LoadUpIGateway;

public class EventActions  {
    private HashMap<String, Event> events; // public private

    // hashmap room key and time as the value
    private HashMap<String, List<String>> roomSchedule; // roomID: date
    private HashMap<String, List<String>> speakerSchedule; // SpeakerID: date
    private HashMap<String, List<String>> attendees; // EventID: attendees
    private GenerateID generate = new GenerateID();


    public EventActions(LoadUpIGateway loader) {
        getAllEvents(loader); // gets all events from message.csv and add those events to a hashmap of all events

    }

    public HashMap<String, Event> getEvents(){
        return events;
    }

    public HashMap<String, List<String>> getSpeakerSchedule(){
        return speakerSchedule;
    }

    public void addSpeakerToSchedule(String speakerID, List<String> times){
        speakerSchedule.put(speakerID, times);
    }

    public HashMap<String, List<String>> getRoomSchedule(){
        return roomSchedule;
    }

    public void addRoomToSchedule(String roomID, List<String> times){
        roomSchedule.put(roomID, times);
    }

    public HashMap<String, List<String>> getAttendees(){
        return attendees;
    }



    /***
     *  gets list of events from the IGateway **/
    private void getAllEvents(LoadUpIGateway loader) {
        List<String> eventList = loader.getEventsList();
        for (String event: eventList){
            String[] eventAttributes = event.split(", ");
            List<String> eventAttendees = Arrays.asList(eventAttributes[3].split("%%"));
            loadEvent(eventAttributes[0], eventAttributes[1], eventAttributes[2], eventAttributes[3],
                    eventAttendees, eventAttributes[5]);
        }
    }

    public boolean createEvent(String title, String speakerId, String dateTime,
                               List<String> attendees, String roomID){
        if (isRoomFree(roomID, dateTime) && isSpeakerFree(speakerId, dateTime)){
            String newID = generate.generateId();
            loadEvent(newID, title, speakerId, dateTime, attendees, roomID);
            return true;
        }
        return false;
    }

    public void setSpeaker(String eventID, String speakerID){
        this.events.get(eventID).setSpeaker(speakerID);
    }

    public void loadEvent(String eventID, String title, String speakerId, String dateTime,
                          List<String> attendees, String roomID){
        Event newEvent = new Event(eventID, title, speakerId, dateTime, attendees, roomID);
        events.put(eventID, newEvent);
        this.attendees.put(eventID, attendees);

        if (speakerSchedule.containsKey(speakerId)){
            speakerSchedule.get(speakerId).add(dateTime);

        } else {
            List<String> speakerTimes = new ArrayList<>();
            speakerTimes.add(dateTime);
            speakerSchedule.put(speakerId, speakerTimes);
        }
        if (roomSchedule.containsKey(roomID)){
            roomSchedule.get(roomID).add(dateTime);

        } else {
            List<String> roomTimes = new ArrayList<>();
            roomTimes.add(dateTime);
            roomSchedule.put(roomID, roomTimes);
        }

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

    public List<String> cancelEvent(String eventID){
        Event event = this.events.get(eventID);
        this.events.remove(eventID);
        List<String> eventAttendees = this.attendees.get(eventID);
        this.attendees.remove(eventID);
        this.speakerSchedule.remove(event.getSpeaker(), event.getDateTime());
        this.roomSchedule.remove(event.getRoomID(), event.getDateTime());
        return eventAttendees;

    }


    public boolean changeEventTime(String eventID, String newDateTime){
        Event event = this.events.get(eventID);
        if(isRoomFree(event.getRoomID(), newDateTime) &&
                isSpeakerFree(event.getSpeaker(), newDateTime)){

            this.speakerSchedule.get(event.getSpeaker()).remove(event.getDateTime());
            this.roomSchedule.get(event.getRoomID()).remove(event.getDateTime());

            event.setDateTime(newDateTime);
            this.speakerSchedule.get(event.getSpeaker()).add(event.getDateTime());
            this.roomSchedule.get(event.getRoomID()).add(event.getDateTime());

            return true;
        }
        return false;

    }

    public boolean isRoomFree(String roomID, String dateTime){
        List<String> roomTime = this.roomSchedule.get(roomID);

        if (roomTime.contains(dateTime)) {
            return false;
        }
        return true;

    }

    public boolean isSpeakerFree(String speakerID, String dateTime){
        List<String> SpeakerTime = this.speakerSchedule.get(speakerID);

        if (SpeakerTime.contains(dateTime)) {
            return false;
        }
        return true;
    }

    // TODO: if event dne: return empty string inside list
    public List<String> eventAttendees(String eventID){

        return attendees.get(eventID);
    }


    public List<String> storeEvents(){
        List<String> storedEvents = new ArrayList();
        for(Map.Entry<String, Event> event : events.entrySet()) {
            storedEvents.add(event.getValue().string());
        }
        return storedEvents;

    }
}
