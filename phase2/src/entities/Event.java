package entities;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;


public class Event {
    private String eventID;
    private String title;
    private String speaker;
    private String dateTime; //  yyyy-mm-dd hh  (24 h)
    private List<String> attendees;
    private String roomID;
    private int capacity;

    /**
     * Instantiates a new attendee object
     * @param eventID the string unique id of this event
     * @param title the string name of this event
     * @param dateTime the string of the date of this event which is yyyy-mm-dd hh an d is 24 hour
     * @param speaker the string of the name of speaker of this event
     * @param attendees the list of people whom this attendee can event
     * @param roomID the string of the ID of the room of this event
     * */


    public Event(String eventID, String title, String speaker, String dateTime,
                 List<String> attendees, String roomID, int capacity){
        this.eventID = eventID;
        this.title = title;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.attendees = attendees;
        this.roomID = roomID;
        this.capacity = capacity;

    }

    /**
     * @return String representation of Event entity
     */
    public String string(){
        // date: year month day hour
        String attendeesString = attendees.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "%%");
        return eventID + "," + title + "," + speaker + "," +
                this.dateTime + "," + attendeesString + "," + roomID;
    }


    /**
     * Return eventID
     * @return ID of event
     * */
    public String getId() {
        return this.eventID;
    }

    /**
     * Return title
     * @return title of event
     * */
    public String getTitle() {
        return this.title;
    }

    /**
     * Return speaker of event
     * @return ID of speaker of event
     * */
    public String getSpeaker() {
        return this.speaker;
    }
    /**
     * set speaker for event
     * @param speaker new speaker's id
     * */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }


    /**
     * Return capacity of event
     * @return capacity of event
     * */
    public int getCapacity() {
        return this.capacity;
    }
    /**
     * set capacity for event
     * @param newCapacity new capacity of event
     * */
    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    /**
     * Return startHour of event
     * @return startHour of event
     * */
    public String getDateTime() {
        return this.dateTime;
    }
    /**
     * set startHour for event
     * @param newDateTime date and hour event starts  yyyy-mm-dd hh  (24 h)
     * */
    public void setDateTime(String newDateTime) {
        this.dateTime = newDateTime;
    }

    /**
     * Return List of attendees of an event
     * @return date and hour event starts  yyyy-mm-dd hh  (24 h)
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
    public String getRoomID() {
        return this.roomID;
    }

}