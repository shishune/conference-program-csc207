package entities;
import java.lang.reflect.Array;
import java.util.List;
import java.time.*;

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

    private String generateEventId() {
        /**
         * Return a unique generated eventID
         * @return ID of an event
         * */
        String id = "M";
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

    public String getEventID() {
        /**
         * Return eventID
         * @return ID of event
         * */
        return eventID;
    }

    public String getTitle() {
        /**
         * Return title
         * @return title of event
         * */
        return title;
    }

    public String getSpeaker() {
        /**
         * Return speaker of event
         * @return ID of speaker of event
         * */
        return speaker;
    }
    public void setSpeaker(String speaker) {
        /**
         * set speaker for event
         * @param speaker
         * */
        this.speaker = speaker;
    }

    public int getStartHour() {
        /**
         * Return startHour of event
         * @return startHour of event
         * */
        return startHour;
    }
    public void setStartHour(int startHour) {
        /**
         * set startHour for event
         * @param startHour
         * */
        this.startHour = startHour;
    }

    public List<String> getAttendees() {
        /**
         * Return List of attendees of an event
         * @return attendees of event
         * */
        return attendees;
    }

    public void setAttendees(List<String> newAttendees) {
        /**
         * Set List of attendees of an event
         * @param newAttendees
         * */
        this.attendees =  newAttendees;
    }

    public boolean addAttendee(String attendeeID){
        /**
         * Add attendeeID to list of attendees, return True if attendee is successfully added
         * (ie was not already in the list of attendees)
         * @param attendeeID
         * @return if attendee is successfully added to list of attendees
         * */
        if (!this.attendees.contains(attendeeID)){
            this.attendees.add(attendeeID);
            return true;
        } else{
            return false;
        }
    }

    public boolean removeAttendee(String attendeeID){
        /**
         * Remove attendeeID to list of attendees, return True if attendee is successfully removed
         * (ie was already in the list of attendees, and is now no longer there)
         * @param attendeeID
         * @return if attendee is successfully removed to list of attendees
         * */
        if (this.attendees.contains(attendeeID)){
            this.attendees.remove(attendeeID);
            return true;
        } else{
            return false;
        }
    }

    // Stores, setter and getter of title of talk
    //Stores, setter and getter list of speaker(s) for this talk
    //Stores, setter and getter of Organizers in charge of this talk
    //Stores, setter and getter for start time of talk
    //Stores, setter and getter for list of attendees for this talk
    //Store, setter and getter of location/room
    //Store, setter and getter of capacity (for now assume 2)


}