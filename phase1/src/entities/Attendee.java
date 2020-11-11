package entities;

import java.util.ArrayList;
import java.util.List;

public class Attendee extends User{

    /**
     * Instantiates a new attendee object
     * @param attendeeId the string unique id of this attendee
     * @param username the string username of this attendee
     * @param password the string password required for attendee to login
     * @param contactsList the list of people whom this attendee can message
     * @param eventList the list of events which this attendee is scheduled to talk
     * @param isLogin boolean whether this attendee has an account
     * @param isOrganizer boolean whether this attendee is an organizer
     * */
    public Attendee (String attendeeId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin, boolean isOrganizer){
        super(attendeeId, username, password, contactsList, eventList, isLogin, isOrganizer); //added eventList and userId to constructor (Jiessie)
    }

    /**
     * Getter for the id unique to this attendee.
     * @return the id of this attendee
     * */
    public String getId() {
        return userId;
    }
}
