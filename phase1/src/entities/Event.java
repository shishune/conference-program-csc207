package entities;
import java.util.List;
import java.util.Date;


public class Event {
    private static String lastEventIdNums = "";
    private String eventID;
    private String title;
    private String speaker;
    private Date dateTime;
    private List<String> attendees;
    private String roomID;

    public Event(String eventID, String title, String speaker, Date dateTime,
                 List<String> attendees, String roomID){
        this.eventID = eventID;
        this.title = title;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.attendees = attendees;
        this.roomID = roomID;

    }

    /**
     * @return String representation of Event entity
     */
    public String string(){
        return eventID + ", " + title + ", " + speaker + ", " + dateTime + ", " +
                attendees.toString() + ", " + roomID;

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
     * Return startHour of event
     * @return startHour of event
     * */
    public Date getDateTime() {
        return this.dateTime;
    }
    /**
     * set startHour for event
     * @param newDateTime hour event starts
     * */
    public void setDateTime(Date newDateTime) {
        this.dateTime = newDateTime;
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
    public String getRoomID() {
        return this.roomID;
    }
    /**
     * set location for event
     * @param location room/location of event
     * */
    public void setLocation(String location) {
        this.roomID = location;
    }


}