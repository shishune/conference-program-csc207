package entities;

import java.util.ArrayList;
import java.util.List;

public class Organizer extends User{
    //private static String lastOrganizerIdNums = "";
    String organizerId; //Todo: why?

    public Organizer (String organizerId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin, boolean isOrganizer){
        super(organizerId, username, password, contactsList, eventList, isLogin, isOrganizer); //added eventList to constructor (Jiessie) added userId to constructor
    }

    /**
     * Return a unique generated organizerID
     * @return ID of an organizer
     * */
    /*public String generateId() {
        String id = "O";
        String lastOrganizerIdNumsSuffix;

        if (lastOrganizerIdNums.equals("")) {
            id = id + "0000000000000000";
            lastOrganizerIdNums = "0000000000000000";
        } else if (lastOrganizerIdNums.equals("0000000000000000")) {
            id = id + "0000000000000001";
            lastOrganizerIdNums = "0000000000000001";
        } else {
            lastOrganizerIdNumsSuffix = lastOrganizerIdNums.replaceAll("^[0]*", "");
            System.out.println(lastOrganizerIdNumsSuffix);

            int currRoomIdNumsSuffix = Integer.parseInt(lastOrganizerIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currRoomIdNumsSuffix).length(); i++) {
                id += "0";
            }
            id += currRoomIdNumsSuffix;
            lastOrganizerIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastOrganizerIdNums);
        return id;
    }*/

    /**
     * Getter for the id unique to this organizer.
     * @return the id of this organizer
     * */
    public String getId() {
        return organizerId;
    }

    /**
     * Add attendeeID to the attendee's contact list
     * @param attendeeID ID of the attendee to be added
     * @param attendee attendee who adds attendeeID to contact list
     * */
    public void addToContact(Attendee attendee, String attendeeID){
        List<String> newContact = attendee.getContactsList();
        newContact.add(attendeeID);
        setContactsList(newContact);
    }

    /**
     * Delete attendeeID from the attendee's contact list
     * @param attendeeID ID of the attendee to be deleted
     * @param attendee attendee who deletes attendeeID from contact list
     * */
    public void deleteContact(Attendee attendee, String attendeeID){
        List<String> newContact = attendee.getContactsList();
        newContact.remove(attendeeID);
        setContactsList(newContact);
    }
}
