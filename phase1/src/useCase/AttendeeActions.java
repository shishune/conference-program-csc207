package useCase;

import entities.*;
import gateway.LoadUpIGateway;
import gateway.LoadUp;
import useCase.GenerateID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// TODO : Please finish the javadocs for the methods that are unique to AttendeeActions and check if the javadocs are correct for the methods moved from UserAccountActions
public class AttendeeActions extends UserAccountActions {

    private HashMap<String, Attendee> attendeesHashMap = new HashMap<String, Attendee>();
    private HashMap<String, Attendee> attendeeUsernameHashMap = new HashMap<String, Attendee>();
    private ArrayList<String> attendees = new ArrayList<String>();
    private LoadUpIGateway loader;
    private SpeakerActions speaker;
    private OrganizerActions organizer;

    public HashMap<String, Attendee> returnIDHashMap() {
        return attendeesHashMap;
    }

    public HashMap<String, Attendee> returnUsernameHashMap() {
        return attendeeUsernameHashMap;
    }

    public AttendeeActions(LoadUpIGateway loader) {
        getAllAttendees(loader); // gets all messages from message.csv
        addAttendeeToHashMap();
        this.loader = loader;
        // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value
    }

    private Attendee createAttendee(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Attendee userAttendee = new Attendee(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userAttendee);
        addUsernameToHashMap(userAttendee);
        return userAttendee;
    }

    public boolean attendeeExists(String username){
        return attendeeUsernameHashMap.containsKey(username);
    }

    public Attendee createAttendee(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID(loader);
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
     * @param addMe the user to be added
     */
    private void addUserIdToHashMap(Attendee addMe) {
        if (attendeesHashMap.containsKey(addMe.getId())) {
            attendeesHashMap.put(addMe.getId(), addMe);
        }
    }

    /**
     * Adds an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     *
     * @param addMe the user to be added
     */
    private void addUsernameToHashMap(Attendee addMe) {

        if (attendeeUsernameHashMap.containsKey(addMe.getUsername())) {
            attendeeUsernameHashMap.put(addMe.getUsername(), addMe);
        }
    }


    /**
     * Removes an userId from existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     *
     * @param removeMe the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     */
    private boolean removeUserIdFromHashMap(Attendee removeMe) {
        if (attendeesHashMap.containsKey(removeMe.getId())) {
            attendeesHashMap.remove(removeMe.getId(), removeMe);
            return true;
        }
        return false;
    }

    /**
     * Removes an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     *
     * @param removeMe the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     */

    private boolean removeUsernameFromHashMap(Attendee removeMe) {
        if (attendeeUsernameHashMap.containsKey(removeMe.getUsername())) {
            attendeeUsernameHashMap.remove(removeMe.getUsername(), removeMe);
            return true;
        }
        return false;
    }


    /**
     * Adds an user to existing list of contacts for an user.
     *
     * @param addMe the username of the user to be added
     * @param toMe  the username of the user who's contact list is updated
     *              ]    * @return true if user is added successfully, false if not
     */

    // TODO We need figure out to check all users that could be added from the different hashmaps which need to be passed in
    public boolean addUserContactList(String toMe, String addMe) {
        Attendee user = attendeeUsernameHashMap.get(toMe);
//<<<<<<< HEAD
        User userOne = attendeeUsernameHashMap.get(addMe);
        //System.out.println("ATTENDEE ACTIONS");
        //System.out.println("USER: " + user);
        //System.out.println("USER: " + userOne);
        //System.out.println(user.getContactsList().contains(userOne.getId()));
        if (attendeeUsernameHashMap != null) {
            boolean isId = user.getContactsList().contains(userOne.getId());
            if (isId) {
                return false;
            } else {
                List<String> toMeContacts = user.getContactsList();
                toMeContacts.add(userOne.getId());
                user.setContactsList(toMeContacts);
                return true;
            }
/**=======
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.putAll(speaker.returnUsernameHashMap());
        userHashMap.putAll(organizer.returnUsernameHashMap());
        userHashMap.putAll(attendeeUsernameHashMap);
        User userOne = userHashMap.get(addMe);
        boolean isId = user.getContactsList().contains(userOne.getId());
        if (isId) {
            return false;
        } else {
            List<String> toMeContacts = user.getContactsList();
            toMeContacts.add(userOne.getId());
            user.setContactsList(toMeContacts);
            return true;
>>>>>>> 136430a0d11dd090478ce252444f6e0997261614**/
        }
        return false;

    }

    /**
     * Removes an user to existing list of contacts from an user.
     *
     * @param removeMe the username of the user to be removed
     * @param toMe     the username of user who's contact list is updated
     * @return true if user is removed successfully, false if not
     */

    public boolean removeUserContactList(String toMe, String removeMe) {
        Attendee user = attendeeUsernameHashMap.get(toMe);
        User userOne = attendeeUsernameHashMap.get(removeMe);
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
     * @param event the event to be removed
     * @param user  the user who's event list is updated
     * @return true if event is removed successfully, false if not
     */

    public boolean removeEventFromUser(String event, String user) {
        Attendee userOne = attendeeUsernameHashMap.get(user);
        boolean isPresent = userOne.getEventList().contains(event);
        if (isPresent) {
            List<String> userEvents = userOne.getEventList();
            userEvents.remove(event);
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
            // System.out.println(user.getEventList().get(i));
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


    private void getAllAttendees(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        attendees = loader.getAllAttendees();
    }

    private void addAttendeeToHashMap() {

        if (attendees != null) {

            for (String attendeeString : attendees) {
                String[] attendeeInfo = attendeeString.split(",");
                ArrayList<String> eventList = new ArrayList<String>();
                ArrayList<String> contactList = new ArrayList<String>();
                String[] events = attendeeInfo[4].split("%%");
                String[] contacts = attendeeInfo[3].split("%%");
                for (String e : events) {
                    eventList.add(e);
                }
                for (String c : contacts) {
                    contactList.add(c);
                }
                Attendee loadedAttendee = new Attendee(attendeeInfo[0], attendeeInfo[1], attendeeInfo[2], contactList,
                        eventList, Boolean.parseBoolean(attendeeInfo[5]), Boolean.parseBoolean(attendeeInfo[6]));
                attendeesHashMap.put(attendeeInfo[0], loadedAttendee);
                attendeeUsernameHashMap.put(attendeeInfo[1], loadedAttendee);
            }

        }
    }

    public ArrayList<String> storingAttendees() {
        ArrayList<String> storedAttendee = new ArrayList<String>();
        if(attendeesHashMap != null && !attendeesHashMap.isEmpty()) {
            for (Map.Entry<String, Attendee> o : attendeesHashMap.entrySet()) {
                storedAttendee.add(o.getValue().stringRepresentation() + "\n");
            }
        }
        return storedAttendee;

    }

    public ArrayList<String> getAttendeeIds() {
        ArrayList<String> storedAttendee = new ArrayList<String>();
        if (attendeesHashMap != null && !attendeesHashMap.isEmpty()) {
            for (Map.Entry<String, Attendee> o : attendeesHashMap.entrySet()) {
                storedAttendee.add(o.getValue().getId() + "\n");
            }
        }
        return storedAttendee;
    }

}
