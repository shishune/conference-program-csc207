package useCases;

import entities.Attendee;
import entities.Organizer;
import entities.Speaker;
import entities.User;

import java.util.HashMap;



/**
 * A use case class that stores a hashmap of users and can add and remove users from the hashmap.
 * Parent class to AttendeeActions, OrganizationActions and SpeakerActions. This class collaborates with user,
 * message, event and conference entity.
 * @author multiple
 * @version 1
 */

public class UserAccountActions {
    // ID HashMaps
    protected HashMap<String, Attendee> attendeeHashMapID = new HashMap<String, Attendee>();
    protected HashMap<String, Organizer> organizerHashMapID = new HashMap<String, Organizer>();
    protected HashMap<String, Speaker> speakerHashMapID = new HashMap<String, Speaker>();

    //Username HashMaps
    protected HashMap<String, Attendee> attendeeHashMapUsername = new HashMap<String, Attendee>();
    protected HashMap<String, Organizer> organizerHashMapUsername = new HashMap<String, Organizer>();
    protected HashMap<String, Speaker> speakerHashMapUsername = new HashMap<String, Speaker>();


    public HashMap<String, Attendee> getAttendeeHashmapID() {
        return attendeeHashMapID;
    }

    public HashMap<String, Organizer> getOrganizerHashMapID() {
        return organizerHashMapID;
    }

    public HashMap<String, Speaker> getSpeakerHashMapID() {
        return speakerHashMapID;
    }

    public HashMap<String, Organizer> getOrganizerHashMapUsername() {
        return organizerHashMapUsername;
    }

    public HashMap<String, Attendee> getAttendeeHashMapUsername() {
        return attendeeHashMapUsername;
    }

    public HashMap<String, Speaker> getSpeakerHashMapUsername() {
        return speakerHashMapUsername;
    }

    /**
     * Adds an user to existing list of contacts for an user.
     * @param addMe the user to be added
     * @param toMe the user who's contact list is updated
     * @return true if user is added successfully, false if not
     * */

    public boolean addUserContactList(String toMe, String addMe, HashMap<String, User> userHashMap) {
        return false;
    }


    /**
     * Removes an user to existing list of contacts from an user.
     * @param removeMe the user to be removed
     * @param toMe the user who's contact list is updated
     * @return true if user is removed successfully, false if not
     * */

    public boolean removeUserContactList(String toMe, String removeMe) {
        return false;
    }


    /**
     * Adds an user to existing list of events for an user.
     * @param event the event to be added
     * @param user the user who's event list is updated
     * @return true if event is added successfully, false if not
     * */

    public boolean addEventToUser(String event, String user){
        return false;
    }


    /**
     * Removes an event from existing list of events from an user.
     * @param event the event to be removed
     * @param user the user who's event list is updated
     * @return true if event is removed successfully, false if not
     * */

    public boolean removeEventFromUser(String event, String user) {
        return false;
    }

    /**
     * Prints all the events in an user's eventList
     * @param user the user who's eventList is printed
     * @return string of all the events a user is attending
     * */

    public String returnAllEvents(String user) {
        return null;
    }

    /**
     * Finds an user from a given username
     * @param username the username given
     * @return user object from hashmap of user objects
     * */
    public User findUserFromUsername(String username){
        return null;
    }

    /**
     * Finds an user from a given userId
     * @param userId the userId given
     * @return user object from hashmap of user objects
     * */
    public User findUserFromId(String userId){
        return null;
    }









































}
