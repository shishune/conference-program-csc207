package useCases;

import entities.Attendee;
import entities.Event;
import entities.User;
import gateways.LoadUpIGateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * A use case class that stores a hashmap of Attendees.
 * Child class to UserAccountActions.
 * @author Jiessie & Mizna
 * @version 1
 */


public class AttendeeActions extends UserAccountActions {
    private HashMap<String, Attendee> attendeesHashMap = new HashMap<String, Attendee>();
    private HashMap<String, Attendee> attendeeUsernameHashMap = new HashMap<String, Attendee>();
    private ArrayList<String> attendees = new ArrayList<String>();
    private LoadUpIGateway loader;
    private SpeakerActions speaker;
    private OrganizerActions organizer;

    /**
     * @return ID of the attendee from the hashmap
     * */
    public HashMap<String, Attendee> returnIDHashMap() {
        return attendeesHashMap;
    }


    /**
     * @return ID of the attendee username from the hashmap
     * */
    public HashMap<String, Attendee> returnUsernameHashMap() {
        return attendeeUsernameHashMap;
    }


    /**
     * @param loader
     * This will load up the data in the hashmap to the CSV files.
     * */
    public AttendeeActions(LoadUpIGateway loader) {
        getAllAttendees(loader); // gets all messages from message.csv
        addAttendeeToHashMap();
        this.loader = loader;
        // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value
    }


    /**
     * @param userId the id of the attendee
     * @param username the username of the attendee
     * @param password the password of the attendee
     * @param contactsList the contact list of the attendee
     * @param eventList the list of events the attending is attending
     * @param isLogin
     * This will create a new Attendee (Will need the overloaded function for phase 2)
     * */
    private Attendee createAttendee(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userAttendee);
        addUsernameToHashMap(userAttendee);
        return userAttendee;
    }


    /**
     * @param username the username of the attendee
     * @return true if the user with the following username exists.
     * */

    public boolean attendeeExists(String username){
        return attendeeUsernameHashMap.containsKey(username);
    }


    /**
     * @param username the username of the attendee
     * @param password the password of the attendee
     * @param contactsList the contact list of the attendee
     * @param eventList the list of events the attendee is attending
     * @param isLogin
     * This will create a new Attendee
     * */
    public Attendee createAttendee(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        useCases.GenerateID generateId = new GenerateID(loader);
        String userId = "A" + generateId.generateId();
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userAttendee);
        addUsernameToHashMap(userAttendee);
        attendeesHashMap.put(userId, userAttendee);
        attendeeUsernameHashMap.put(username, userAttendee);
        return userAttendee;
    }


    /**
     * Adds an userId to existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     *
     * @param userId the user to be added
     */
    private void addUserIdToHashMap(Attendee userId) {
        if (attendeesHashMap.containsKey(userId.getId())) {
            attendeesHashMap.put(userId.getId(), userId);
        }
    }


    /**
     * Adds an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     *
     * @param username the user to be added
     */
    private void addUsernameToHashMap(Attendee username) {

        if (attendeeUsernameHashMap.containsKey(username.getUsername())) {
            attendeeUsernameHashMap.put(username.getUsername(), username);
        }
    }


    /**
     * Removes an userId from existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     *
     * @param removeUserId the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     */
    private boolean removeUserIdFromHashMap(Attendee removeUserId) {
        if (attendeesHashMap.containsKey(removeUserId.getId())) {
            attendeesHashMap.remove(removeUserId.getId(), removeUserId);
            return true;
        }
        return false;
    }


    /**
     * Removes an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     *
     * @param removeUsername the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     */

    private boolean removeUsernameFromHashMap(Attendee removeUsername) {
        if (attendeeUsernameHashMap.containsKey(removeUsername.getUsername())) {
            attendeeUsernameHashMap.remove(removeUsername.getUsername(), removeUsername);
            return true;
        }
        return false;
    }


    /**
     * Adds an user to existing list of contacts for an user.
     *
     * @param sender the username of the user to be added
     * @param receiver  the username of the user who's contact list is updated
     * @return true if user is added successfully, false if not
     */

    public boolean addUserContactList(String receiver, String sender, HashMap<String, User> userUsernameHashMap) {
        User user = userUsernameHashMap.get(receiver);
        User userOne = userUsernameHashMap.get(sender);
        if(userOne == null || userOne.getId() == null) {
            return false;
        }
        boolean isId = user.getContactsList().contains(userOne.getId());
        if (user.getId() == userOne.getId() || isId) {
            return false;
        } else {
            List<String> toMeContacts = user.getContactsList();
            toMeContacts.add(userOne.getId());
            user.setContactsList(toMeContacts);
            return true;
        }
    }



    /**
     * Removes an user to existing list of contacts from an user.
     *
     * @param removedUsername the username of the user to be removed
     * @param receiver     the username of user who's contact list is updated
     * @return true if user is removed successfully, false if not
     */

    public boolean removeUserContactList(String receiver, String removedUsername) {
        Attendee user = attendeeUsernameHashMap.get(receiver);
        User userOne = attendeeUsernameHashMap.get(removedUsername);
        boolean isPresent = user.getContactsList().contains(userOne.getId());
        if (!isPresent) {
            return false;
        } else {
            List<String> toMeContacts = user.getContactsList();
            toMeContacts.remove(userOne.getId());
            user.setContactsList(toMeContacts);
            return true;
        }
    }


    /**
     * Adds an user to existing list of events for an user.
     *
     * @param event the name of the event to be added
     * @param user  the username of the user who's event list is updated
     * @return true if event is added successfully, false if not
     */

    public boolean addEventToUser(String event, String user) {
        User userOne = attendeeUsernameHashMap.get(user);
        boolean isPresent = userOne.getEventList().contains(event);
        if (isPresent) {
            return false;
        } else {
            List<String> userEvents = userOne.getEventList();
            userEvents.add(event);
            userOne.setEventList(userEvents);
            return true;
        }
    }


    /**
     * Removes an event from existing list of events from an user.
     *
     * @param eventID the event to be removed
     * @param userID  the user who's event list is updated
     * @return true if event is removed successfully, false if not
     */

    public boolean removeEventFromUser(String eventID, String userID) {
        Attendee userOne = attendeesHashMap.get(userID);
        boolean isPresent = userOne.getEventList().contains(eventID);
        if (isPresent) {
            List<String> userEvents = userOne.getEventList();
            userEvents.remove(eventID);
            userOne.setEventList(userEvents);
            return true;
        }
        return false;
    }


    /**
     * Prints all the events in an user's eventList
     *
     * @param user the user who's eventList is printed
     * @return string of all the events a user is attending
     */

    public String returnAllEvents(String user) {
        Attendee userOne = attendeeUsernameHashMap.get(user);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < userOne.getEventList().size(); i++) {
            String a = userOne.getEventList().get(i);
            result.append(a).append(' ');
        }
        return result.toString();
    }


    /**
     * Finds an user from a given username
     *
     * @param username the username given
     * @return user object from hashmap of user objects
     */

    public Attendee findUserFromUsername(String username) {
        return attendeeUsernameHashMap.get(username);
    }


    /**
     * Finds an user from a given userId
     *
     * @param userId the userId given
     * @return user object from hashmap of user objects
     */
    public Attendee findUserFromId(String userId) {
        return attendeesHashMap.get(userId);
    }


    /**
     * It will get all attendees from the CSV file.
     * @param loader the userId given
     */
    private void getAllAttendees(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        attendees = loader.getAllAttendees();
    }


    /**
     * This method will add the attendee to the hashmap.
     */
    private void addAttendeeToHashMap() {

        if (attendees != null) {

            for (String attendeeString : attendees) {
                String[] attendeeInfo = attendeeString.split(",");
                ArrayList<String> eventList = new ArrayList<String>();
                ArrayList<String> contactList = new ArrayList<String>();
                String[] events = attendeeInfo[4].split("%%");
                String[] contacts = attendeeInfo[3].split("%%");
                for (String e : events) {
                    if (!e.equals("")) {
                        eventList.add(e);
                    }
                }
                for (String c : contacts) {
                    if (!c.equals("")) {
                        contactList.add(c);
                    }
                }
                Attendee loadedAttendee = new Attendee(attendeeInfo[0], attendeeInfo[1], attendeeInfo[2], contactList,
                        eventList, Boolean.parseBoolean(attendeeInfo[5]), Boolean.parseBoolean(attendeeInfo[6]));
                attendeesHashMap.put(attendeeInfo[0], loadedAttendee);
                attendeeUsernameHashMap.put(attendeeInfo[1], loadedAttendee);
            }

        }
    }


    /**
     * It will be storing attendees
     * @return ArrayList<String>
     */
    public ArrayList<String> storingAttendees() {
        ArrayList<String> storedAttendee = new ArrayList<String>();
        if(attendeesHashMap != null && !attendeesHashMap.isEmpty()) {
            for (Map.Entry<String, Attendee> o : attendeesHashMap.entrySet()) {
                storedAttendee.add(o.getValue().stringRepresentation() + "\n");
            }
        }
        return storedAttendee;
    }


    /**
     * It will be get the attendee ID
     * @return ArrayList<String>
     */
    public ArrayList<String> getAttendeeIds() {
        ArrayList<String> storedAttendee = new ArrayList<String>();
        if (attendeesHashMap != null && !attendeesHashMap.isEmpty()) {
            for (Map.Entry<String, Attendee> o : attendeesHashMap.entrySet()) {
                storedAttendee.add(o.getValue().getId() + "\n");
            }
        }
        return storedAttendee;
    }

    /**
     * Adds an user to existing list of saved events for an user.
     *
     * @param event the name of the event to be added
     * @param user  the username of the user who's event list is updated
     * @return true if event is added successfully, false if not
     */

    public boolean addEventToSavedEvent(String event, String user) {
        Attendee userOne = attendeeUsernameHashMap.get(user);
        boolean isPresent = userOne.getSavedEventList().contains(event);
        if (isPresent) {
            return false;
        } else {
            List<String> userSavedEvents = userOne.getSavedEventList();
            userSavedEvents.add(event);
            userOne.setEventList(userSavedEvents);
            return true;
        }
    }

    /**
     * Removes an event from existing list of saved events from an user.
     *
     * @param eventID the event to be removed
     * @param userID  the user who's event list is updated
     * @return true if event is removed successfully, false if not
     */

    public boolean removeEventFromSavedEvent(String eventID, String userID) {
        Attendee userOne = attendeesHashMap.get(userID);
        boolean isPresent = userOne.getSavedEventList().contains(eventID);
        if (isPresent) {
            List<String> userSavedEvents = userOne.getSavedEventList();
            userSavedEvents.remove(eventID);
            userOne.setSavedEventList(userSavedEvents);
            return true;
        }
        return false;
    }


}
