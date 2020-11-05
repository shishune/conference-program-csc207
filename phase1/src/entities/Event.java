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

    public Event(String eventID, String title, String speaker, int startHour,
                 List<String> attendees, String location){
        this.eventID = eventID;
        this.title = title;
        this.speaker = speaker;
        this.startHour = startHour;
        this.attendees = attendees;
        this.location = location;

    }


    /**
     * Return eventID
     * @return ID of event
     * */
    public String getId() {
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


}