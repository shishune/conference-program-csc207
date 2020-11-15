package useCase;
import java.util.*;

import entities.*;
import gateway.LoadUpIGateway;


public class EventActions  {
    private HashMap<String, Event> events = new HashMap<String, Event>(); // public private

    // hashmap room key and time as the value
    private HashMap<String, List<String>> roomSchedule = new HashMap<String, List<String>>(); // roomID: date
    private HashMap<String, List<String>> speakerSchedule = new HashMap<String, List<String>>(); // SpeakerID: date
    private HashMap<String, List<String>> attendees = new HashMap<String, List<String>>(); // EventID: attendees
    private LoadUpIGateway loader;
    private GenerateID generate = new GenerateID(loader);
    private List<String> eventList;


    public EventActions(LoadUpIGateway loader) {
        loadAllEvents(loader); // gets all events from message.csv and add those events to a hashmap of all events
        addLoadedToHashMap();
        this.loader = loader;

    }

    /***
     * return event of given ID
     * @param eventID
     * @return events
     */
    public Event getEvent(String eventID){
        return events.get(eventID);
    }

    /***
     * return if the event exists in events
     * @param eventID
     * @return true if the event exists in events
     */
    public boolean eventExists(String eventID){
        return events.containsKey(eventID);
    }

    /***
     * return hashmap of all eventIDs and the corresponding event object. key: speakerID value: List of date and time
     * @return hashmap of all eventIDs and the corresponding event object
     */
    public HashMap<String, Event> getEvents(){
        return events;
    }

    /***
     * return hashmap of all speakers and the times they are busy. key: speakerID value: List of date and time
     * @return hashmap of all speakers and the times they are busy
     */
    public HashMap<String, List<String>> getSpeakerSchedule(){
        return speakerSchedule;
    }


    /***
     * Add a new speaker to the speakerSchedule
     * @param speakerID ID of speaker
     */
    public void addSpeakerToSchedule(String speakerID){
        speakerSchedule.put(speakerID, new ArrayList<>());
    }

    /***
     * return hashmap of all rooms and the times they are in use. key: roomID value: List of date and time
     * @return hashmap of all rooms and the times they are in use
     */
    public HashMap<String, List<String>> getRoomSchedule(){
        return roomSchedule;
    }

    /***
     * Add a new room to the roomSchedule
     * @param roomID ID of room
     */
    public void addRoomToSchedule(String roomID){
        roomSchedule.put(roomID, new ArrayList<>());
    }

    /** gets list of event from the IGateway **/
    private void loadAllEvents(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        eventList = loader.getEventsList();
    }

    /** Adds events loaded from the csv to <events> **/
    private void addLoadedToHashMap() {
        //System.out.println(conversations);
        if (eventList != null) {
            for (String event: eventList){
                String[] eventAttributes = event.split(", ");
                List<String> eventAttendees = Arrays.asList(eventAttributes[3].split("%%"));
                loadEvent(eventAttributes[0], eventAttributes[1], eventAttributes[2], eventAttributes[3],
                        eventAttendees, eventAttributes[5]);
            }
        }
    }


    /***
     * gets list of events from the IGateway
     * @param loader
     */
    private void getAllEvents(LoadUpIGateway loader) {
        List<String> eventList = loader.getEventsList();
        for (String event: eventList){
            String[] eventAttributes = event.split(", ");
            List<String> eventAttendees = Arrays.asList(eventAttributes[3].split("%%"));
            loadEvent(eventAttributes[0], eventAttributes[1], eventAttributes[2], eventAttributes[3],
                    eventAttendees, eventAttributes[5]);
        }
    }

    // edited to interact well with presenter
    public List<Boolean> createEvent(String title, String speakerId, String dateTime,
                               List<String> attendees, String roomID){
        List<Boolean> checks = new ArrayList<Boolean>();
        if (isRoomFree(roomID, dateTime) && isSpeakerFree(speakerId, dateTime)){
            String newID = generate.generateId();
            loadEvent(newID, title, speakerId, dateTime, attendees, roomID);
            checks.add(true);
            return checks;
        }
        checks.add(false);
        if (!isRoomFree(roomID, dateTime)){
            checks.add(true);
        }
        else if(!isSpeakerFree(speakerId, dateTime)){
            checks.add(false);
        }
        return checks;
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


    public List<String> storeEvents(){
        List<String> storedEvents = new ArrayList();
        for(Map.Entry<String, Event> event : events.entrySet()) {
            storedEvents.add(event.getValue().string());
        }
        return storedEvents;

    }

    public List<String> getEventAttendees(String eventID){

        return attendees.get(eventID);
    }

}
