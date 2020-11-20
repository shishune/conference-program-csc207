package useCase;
import java.util.*;

import entities.*;
import gateway.LoadUpIGateway;


public class EventActions  {
    private HashMap<String, Event> events = new HashMap<String, Event>(); // public private
    private HashMap<String, Event> eventNames = new HashMap<String, Event>();

    // hashmap room key and time as the value
    private HashMap<String, List<String>> roomSchedule = new HashMap<String, List<String>>(); // roomID: date

    private HashMap<String, List<String>> speakerSchedule = new HashMap<String, List<String>>(); // SpeakerID: date
    private HashMap<String, List<String>> attendees = new HashMap<String, List<String>>(); // EventID: attendees
    private LoadUpIGateway loader;
    //private GenerateID generate = new GenerateID(loader);
    private List<String> eventList;


    public EventActions(LoadUpIGateway loader) {
        loadAllEvents(loader); // gets all events from message.csv and add those events to a hashmap of all events
        addLoadedToHashMap();
        this.loader = loader;
        roomSchedule.put("Toronto", new ArrayList<String>());
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
     * return hashmap of all eventNames and the corresponding event object. key: speakerID value: List of date and time
     * @return hashmap of all eventNames and the corresponding event object
     */
    public HashMap<String, Event> getEventNames(){
        return eventNames;
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
    public boolean addRoomToSchedule(String roomID){
        roomSchedule.put(roomID, new ArrayList<>());
        return roomSchedule.containsKey(roomID);
    }

    /** gets list of event from the IGateway **/
    private void loadAllEvents(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        eventList = loader.getEvents();
    }

    /** Adds events loaded from the csv to <events> **/
    private void addLoadedToHashMap() {
        //System.out.println(conversations);
        if (eventList != null && !eventList.isEmpty()) {
            for (String event: eventList){
                String[] eventAttributes = event.split(",");
                    List<String> eventAttendees = Arrays.asList(eventAttributes[4].split("%%"));
                    loadEvent(eventAttributes[0], eventAttributes[1], eventAttributes[2], eventAttributes[3],
                            eventAttendees, eventAttributes[5]);
            }
        }
    }


//    /***
//     * gets list of events from the IGateway
//     * @param loader
//     */
//    private void getAllEvents(LoadUpIGateway loader) {
//        List<String> eventList = loader.getEventsList();
//        for (String event: eventList){
//            String[] eventAttributes = event.split(",");
//            List<String> eventAttendees = Arrays.asList(eventAttributes[3].split("%%"));
//            loadEvent(eventAttributes[0], eventAttributes[1], eventAttributes[2], eventAttributes[3],
//                    eventAttendees, eventAttributes[5]);
//        }
//    }

    /**
     * Create a new event based on the parmeters provided
     * @return true if the event was created
     * */
    // edited to interact well with presenter
    public Event createEvent(String title, String speakerId, String dateTime,
                               List<String> attendees, String roomID){
        if (isRoomFree(roomID, dateTime) && isSpeakerFree(speakerId, dateTime)){
            GenerateID generateId = new GenerateID(loader);
            String newID = "E" + generateId.generateId();
            System.out.println(newID);
            return loadEvent(newID, title, speakerId, dateTime, attendees, roomID);
        }
        return null;

    }

    /***
     * set speaker of an event
     * @param eventID if of event
     * @param speakerID id of new speaker
     */
    public void setSpeaker(String eventID, String speakerID){
        this.events.get(eventID).setSpeaker(speakerID);
    }

    /***
     *  Load a new event to CSV file so we are able to access it after the system has been closed as well
     *
     * @param eventID id of event
     * @param title title of event
     * @param speakerId id of speaker
     * @param dateTime date and time of event
     * @param attendees list of attendees of event
     * @param roomID id of room
     */
    public Event loadEvent(String eventID, String title, String speakerId, String dateTime,
                          List<String> attendees, String roomID){
        if (attendees.get(0).equals("") && attendees.size() == 1){ // not certain second one is necessary
            attendees = new ArrayList<>();
        }
        Event newEvent = new Event(eventID, title, speakerId, dateTime, attendees, roomID);
        events.put(eventID, newEvent);
        eventNames.put(title, newEvent);
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
        return newEvent;
    }

    /***
     * Add an attendee who has requested to be added to a specific event
     * @param eventID
     * @param attendeeID
     * @return true if an attendee has been added to their desired event
     */
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
    /***
     * Remove an attendee who has requested to be removed to a specific event
     * @param eventID
     * @param attendeeID
     * @return true if an attendee has been removed to their desired event
     */
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


    /***
     * Remove an attendee who has requested to be removed from a specfic event
     * @param eventID
     * @return true if an attendee has been removed from an event that they used to be a part of
     */
    public List<String> cancelEvent(String eventID){
        Event event = this.events.get(eventID);
        this.events.remove(eventID);
        List<String> eventAttendees = this.attendees.get(eventID);
        this.attendees.remove(eventID);
        this.speakerSchedule.remove(event.getSpeaker(), event.getDateTime());
        this.roomSchedule.remove(event.getRoomID(), event.getDateTime());
        return eventAttendees;

    }


    /***
     * Cancel an event
     * @param eventID
     * @param newDateTime
     * @return List<String> which is the list of attendees who had signed up for that event
     */
    public boolean changeEventTime(String eventID, String newDateTime){
        Event event = this.events.get(eventID);
        if(event != null) {
            if (isRoomFree(event.getRoomID(), newDateTime) &&
                    isSpeakerFree(event.getSpeaker(), newDateTime)) {

                this.speakerSchedule.get(event.getSpeaker()).remove(event.getDateTime());
                this.roomSchedule.get(event.getRoomID()).remove(event.getDateTime());

                event.setDateTime(newDateTime);
                this.speakerSchedule.get(event.getSpeaker()).add(event.getDateTime());
                this.roomSchedule.get(event.getRoomID()).add(event.getDateTime());

                return true;
            }
        }
        return false;

    }

    /**
     * Change the time of an event which has been created
     * @param roomID
     * @param dateTime
     * @return true if the event has been successfuly updated
     * */

    public boolean isRoomFree(String roomID, String dateTime){
        List<String> roomTime = this.roomSchedule.get(roomID);

        if (roomTime != null && roomTime.contains(dateTime)) {
            return false;
        }
        return true;

    }

    /**
     * Check if a room is available as a certain time
     * @param speakerID
     * @param dateTime
     * @return true if the room is in fact available
     * */
    public boolean isSpeakerFree(String speakerID, String dateTime){
        List<String> SpeakerTime = this.speakerSchedule.get(speakerID);

        if (SpeakerTime != null && SpeakerTime.contains(dateTime)) {
            return false;
        }
        return true;
    }

    /***
     * return all events in hashmap as a List of strings
     * @return all events in hashmap as a List of strings
     */
    public List<String> storeEvents(){
        List<String> storedEvents = new ArrayList();
        for(Map.Entry<String, Event> event : events.entrySet()) {
            storedEvents.add(event.getValue().string()+"\n");
        }
        return storedEvents;

    }

    public ArrayList<String> getEventIds() {
        ArrayList<String> storedE = new ArrayList<String>();
        if (events != null && !events.isEmpty()) {
            for (Map.Entry<String, Event> o : events.entrySet()) {
                storedE.add(o.getValue().getId() + "\n");
            }
        }
        return storedE;
    }

    /***
     * return list of all attendee IDs of an event
     * @param eventID
     * @return List of all attendee IDs of an event
     */
    public List<String> getEventAttendees(String eventID){
        return attendees.get(eventID);
    }

}
