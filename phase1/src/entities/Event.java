package entities;
import java.util.List;


public class Event {
    private static String lastEventIdNums = "";
    private String eventID;
    private String title;
    private String speaker;
    private int startHour;
    private List<String> attendees;
    private String location;
    private static int capacity = 2;

    public Event(String title, String speaker, int startHour,
                 List<String> attendees, String location){
        this.eventID = generateEventId();
        this.title = title;
        this.speaker = speaker;
        this.startHour = startHour;
        this.attendees = attendees;
        this.location = location;

    }

    /**
     * Return a unique generated eventID
     * @return ID of an event
     * */
    private String generateEventId() {
        String id = "E";
        String lastEventIdNumsSuffix;

        if (lastEventIdNums == "") {
            id = id + "0000000000000000";
            lastEventIdNums = "0000000000000000";
        } else if (lastEventIdNums == "0000000000000000") {
            id = id + "0000000000000001";
            lastEventIdNums = "0000000000000001";
        } else {
            lastEventIdNumsSuffix = lastEventIdNums.replaceAll("^[0]*", "");
            System.out.println(lastEventIdNumsSuffix);

            int currMessageIdNumsSuffix = Integer.parseInt(lastEventIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currMessageIdNumsSuffix).length(); i++) {
                id += "0";
            }
            id += currMessageIdNumsSuffix;
            lastEventIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastEventIdNums);
        return id;
    }

    /**
     * Return eventID
     * @return ID of event
     * */
    public String getEventID() {
        return eventID;
    }

    /**
     * Return title
     * @return title of event
     * */
    public String getTitle() {
        return title;
    }

    /**
     * Return speaker of event
     * @return ID of speaker of event
     * */
    public String getSpeaker() {
        return speaker;
    }
    /**
     * set speaker for event
     * @param speaker new speaker's id
     * */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    /**
     * Return startHour of event
     * @return startHour of event
     * */
    public int getStartHour() {
        return startHour;
    }
    /**
     * set startHour for event
     * @param startHour hour event starts
     * */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * Return List of attendees of an event
     * @return attendees of event
     * */
    public List<String> getAttendees() {
        return attendees;
    }
    /**
     * Set List of attendees of an event
     * @param newAttendees list of IDs of the new attendee
     * */
    public void setAttendees(List<String> newAttendees) {
        this.attendees =  newAttendees;
    }

    /**
     * Add attendeeID to list of attendees, return True if attendee is successfully added
     * (ie was not already in the list of attendees)
     * @param attendeeID ID of the attendee to be added
     * @return if attendee is successfully added to list of attendees
     * */
    public boolean addAttendee(String attendeeID){
        if (!this.attendees.contains(attendeeID)){
            this.attendees.add(attendeeID);
            return true;
        } else{
            return false;
        }
    }

    /**
     * Remove attendeeID to list of attendees, return True if attendee is successfully removed
     * (ie was already in the list of attendees, and is now no longer there)
     * @param attendeeID ID of the attendee to be removed
     * @return if attendee is successfully removed to list of attendees
     * */
    public boolean removeAttendee(String attendeeID){
        if (this.attendees.contains(attendeeID)){
            this.attendees.remove(attendeeID);
            return true;
        } else{
            return false;
        }
    }

    /**
     * Return location of event (room)
     * @return location of event
     * */
    public String getLocation() {
        return location;
    }
    /**
     * set location for event
     * @param location room/location of event
     * */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Return capacity of event
     * @return maximum number of people who can attend event
     * */
    public static int getCapacity() {
        return capacity;
    }
    /**
     * set capacity for event
     * @param capacity maximum number of people who can attend event
     * */
    public static void setCapacity(int capacity) {
        Event.capacity = capacity;
    }

    // Stores, setter and getter of title of talk
    //Stores, setter and getter list of speaker(s) for this talk
    //Stores, setter and getter for start time of talk
    //Stores, setter and getter for list of attendees for this talk
    //Store, setter and getter of location/room
    //Store, setter and getter of capacity (for now assume 2)


}