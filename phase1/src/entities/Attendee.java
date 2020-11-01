package entities;

import java.util.ArrayList;
import java.util.List;

public class Attendee extends User{
    private boolean isOrganizer;
    private String attendeeId;
    private List<String> eventList;
    private static String lastAttendeeIdNums = "";

    public Attendee (String username, String password, List<String> contactsList, boolean isLogin, boolean isOrganizer){
        super(username, password, contactsList, isLogin);
        this.isOrganizer = isOrganizer;
        this.attendeeId = generateId();
        eventList = new ArrayList<>();
    }

    /**
     * Return a unique generated AttendeeID
     * @return ID of an attendee
     * */
    public String generateId() {
        String id = "A";
        String lastAttendeeIdNumsSuffix;

        if (lastAttendeeIdNums.equals("")) {
            id = id + "0000000000000000";
            lastAttendeeIdNums = "0000000000000000";
        } else if (lastAttendeeIdNums.equals("0000000000000000")) {
            id = id + "0000000000000001";
            lastAttendeeIdNums = "0000000000000001";
        } else {
            lastAttendeeIdNumsSuffix = lastAttendeeIdNums.replaceAll("^[0]*", "");
            System.out.println(lastAttendeeIdNumsSuffix);

            int currAttendeeIdNumsSuffix = Integer.parseInt(lastAttendeeIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currAttendeeIdNumsSuffix).length(); i++) {
                id += "0";
            }
            id += currAttendeeIdNumsSuffix;
            lastAttendeeIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastAttendeeIdNums);
        return id;
    }

    /**
     * Getter for the id unique to this attendee.
     * @return the id of this attendee
     * */
    public String getAttendeeId() {
        return attendeeId;
    }

    /**
     * Add attendeeID to this attendee's contact list
     * @param attendeeID ID of the attendee to be added
     * */
    public void addToContact(String attendeeID){
        List<String> newContact = getContactsList();
        newContact.add(attendeeID);
        setContactsList(newContact);
    }

    /**
     * Delete attendeeID from this attendee's contact list
     * @param attendeeID ID of the attendee to be deleted
     * */
    public void deleteContact(String attendeeID){
        List<String> newContact = getContactsList();
        newContact.remove(attendeeID);
        setContactsList(newContact);
    }

    /**
     * Getter for the list of events this attendee signed up for
     * @return the list of events
     * */
    public List<String> getEventList() {
        return eventList;
    }

    /**
     * Sign up for the event
     * @param eventID ID of the event to sign up
     * */
    public void signUpFor(String eventID){
        this.eventList.add(eventID);
    }

    /**
     * Cancel the enrolment of the event
     * @param eventID ID of the event to be cancelled
     * */
    public void cancelEnrolmentIn(String eventID){
        this.eventList.remove(eventID);
    }


}
