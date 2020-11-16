package useCase;

import entities.*;

import gateway.LoadUpIGateway;
import useCase.EventActions;
import gateway.LoadUp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: change return type to organizer instead of user (do so for attendee, and speaker as well)
        // (i did it organizer and attendee but please check them over, all that's left is for speaker)
// TODO : Please finish the javadocs for the methods that are unique to OrganizerActions and check if the javadocs are correct for the methods moved from UserAccountActions

public class OrganizerActions extends UserAccountActions {

    private HashMap<String, Organizer> organizerHashMap = new HashMap<String, Organizer>();
    private HashMap<String, Organizer> organizerUsernameHashMap = new HashMap<String, Organizer>();
    private ArrayList<String> organizers = new ArrayList<String>();
    private LoadUpIGateway loader;


    public HashMap<String, Organizer> returnOrganizersHashMap(){
        return organizerHashMap;
    }

    public HashMap<String, Organizer> returnOrganizersUsernameHashMap(){
        return organizerUsernameHashMap;
    }

    // private LoadUp loader = new LoadUp();

    public OrganizerActions(LoadUpIGateway loader) {
        // this.loader = loader;
        getAllOrganizer(loader); // gets all messages from message.csv
        addOrganizerToHashMap(); // adds those messages to a hashmap of all messages from the csv
        // with message ID as key and message object as value

    }

    public Organizer createOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, false);
        addUserIdToHashMap(userOrganizer);
        addUsernameToHashMap(userOrganizer);
//        super.addUserIdToHashMap(userOrganizer);
//        super.addUsernameToHashMap(userOrganizer);
        return userOrganizer;
    }

    public Organizer loadOrganizer(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        Organizer userOrganizer = new Organizer(userId, username, password, contactsList, eventList, isLogin, true);
        addUserIdToHashMap(userOrganizer);
        addUsernameToHashMap(userOrganizer);
        organizerHashMap.put(userId, userOrganizer);
        organizerUsernameHashMap.put(username, userOrganizer);
        return userOrganizer;
    }

    public Organizer addToOrganizerHashMap() {

        return null;
    }


    public User createOrganizer(String username, String password, boolean isLogin){
        GenerateID generateId = new GenerateID(loader);
        String userId = "O" + generateId;
        return loadOrganizer(userId, username, password, new ArrayList<String>(), new ArrayList<String>(), false);
    }

//    public boolean addNewEvent(Event event) {
//        EventActions eventactions = new EventActions();
//        eventactions.loadEvent(event.getId(), event.getTitle(), event.getSpeaker(), event.getDateTime(),
//                event.getAttendees(), event.getRoomID());
//
//        List<String> eventList = loader.getEventsList();
//        return eventList.contains(event.getId());
//    }
//
//    public boolean changeEventTime(String eventId, String newDateTime) {
//        EventActions eventactions = new EventActions();
//        return eventactions.changeEventTime(eventId, newDateTime);
//    }

//    public List<String> cancelEvent(String eventId) {
//        EventActions eventactions = new EventActions();
//        return eventactions.cancelEvent(eventId);
//    }


    /**
     * Adds an userId to existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     * @param addMe the user to be added
     * */
    public void addUserIdToHashMap(Organizer addMe){
        if (organizerUsernameHashMap.containsKey(addMe.getId())){
            organizerUsernameHashMap.put(addMe.getId(), addMe);
        }

    }

    /**
     * Adds an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     * @param addMe the user to be added
     * */
    protected void addUsernameToHashMap(Organizer addMe){

        if (organizerUsernameHashMap.containsKey(addMe.getUsername())){
            organizerUsernameHashMap.put(addMe.getUsername(), addMe);
        }

    }

    /**
     * Removes an userId from existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     * @param removeMe the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     * */
    public boolean removeUserIdFromHashMap(Organizer removeMe){
        if (organizerUsernameHashMap.containsKey(removeMe.getId())){
            organizerUsernameHashMap.remove(removeMe.getId(), removeMe);
            return true;
        }
        return false;
    }

    /**
     * Removes an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     * @param removeMe the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     * */

    public boolean removeUsernameFromHashMap(Organizer removeMe){
        if (organizerUsernameHashMap.containsKey(removeMe.getUsername())){
            organizerUsernameHashMap.remove(removeMe.getUsername(), removeMe);
            return true;
        }
        return false;
    }

    /**
     * Adds an user to existing list of contacts for an user.
     * @param addMe the user to be added
     * @param toMe the user who's contact list is updated
     * @return true if user is added successfully, false if not
     * */


    public boolean addUserContactList(String toMe, String addMe) {
        Organizer user = organizerUsernameHashMap.get(toMe);
        Organizer userOne = organizerUsernameHashMap.get(addMe);
        boolean isId = user.getContactsList().contains(userOne.getId());
        if (isId) {
            return false;
        }
        else {
            List<String> toMeContacts = user.getContactsList();
            toMeContacts.add(userOne.getId());
            user.setContactsList(toMeContacts);
            return true;
        }}

    /**
     * Removes an user to existing list of contacts from an user.
     * @param removeMe the user to be removed
     * @param toMe the user who's contact list is updated
     * @return true if user is removed successfully, false if not
     * */

    public boolean removeUserContactList(String toMe, String removeMe) {
        User user = organizerUsernameHashMap.get(toMe);
        User userOne = organizerUsernameHashMap.get(removeMe);
        boolean isPresent = user.getContactsList().contains(userOne.getId());
        if (!isPresent) {
            return false;
        }
        else {
            List<String> toMeContacts = user.getContactsList();
            toMeContacts.remove(userOne.getId());
            user.setContactsList(toMeContacts);
            return true;
        }}


    /**
     * Adds an user to existing list of events for an user.
     * @param event the event to be added
     * @param user the user who's event list is updated
     * @return true if event is added successfully, false if not
     * */

    public boolean addEventToUser(String event, String user) {
        Organizer userOne = organizerUsernameHashMap.get(user);
        boolean isPresent = userOne.getEventList().contains(event);
        if (isPresent) {
            return false;
        }
        else{
            List<String> userEvents = userOne.getEventList();
            userEvents.add(event);
            userOne.setEventList(userEvents);
            return true;
        }}

    /**
     * Removes an event from existing list of events from an user.
     * @param event the event to be removed
     * @param user the user who's event list is updated
     * @return true if event is removed successfully, false if not
     * */

    public boolean removeEventFromUser(String event, String user) {
        Organizer userOne = organizerUsernameHashMap.get(user);
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
     * Finds an user from a given username
     * @param username the username given
     * @return user object from hashmap of user objects
     * */
    public User findUserFromUsername(String username){
        return organizerUsernameHashMap.get(username);
    }


    /**
     * Finds an user from a given userId
     * @param userId the userId given
     * @return user object from hashmap of user objects
     * */
    public User findUserFromId(String userId){
        return organizerHashMap.get(userId);
    }


    /**
     * Returns all the events in an user's eventList
     *
     * @param user the user who's eventList is printed
     * @return string of all the events a user is attending
     */

    public String returnAllEvents(String user) {
        {
            User userOne = organizerUsernameHashMap.get(user);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < userOne.getEventList().size(); i++) {
                // System.out.println(user.getEventList().get(i));
                String a = userOne.getEventList().get(i);
                result.append(a).append(' ');
            }
            return result.toString();
        }
    }
    private void getAllOrganizer(LoadUpIGateway loader) {
        //LoadUp loader = new LoadUp(); // this is okay because IGateway
        organizers = loader.getAllOrganizers();
    }

    private void addOrganizerToHashMap() {
        if (organizers != null) {
            for (String organizersString : organizers) {
                String[] organizerInfo = organizersString.split(",");
                ArrayList<String> eventList = new ArrayList<String>();
                ArrayList<String> contactList = new ArrayList<String>();
                String[] events = organizerInfo[4].split("%%");
                String[] contacts = organizerInfo[3].split("%%");

                for (String e : events) {
                    eventList.add(e);
                }

                for (String c : contacts) {
                    contactList.add(c);
                }

//                Organizer loadedOrganizer = new Organizer(organizerInfo[0], organizerInfo[1], organizerInfo[2], contactList,
//                        eventList, Boolean.parseBoolean(organizerInfo[5]), Boolean.parseBoolean(organizerInfo[6]));
                loadOrganizer(organizerInfo[0], organizerInfo[1], organizerInfo[2], contactList,
                        eventList, Boolean.parseBoolean(organizerInfo[5]));
//                organizerHashMap.put(organizerInfo[0], loadedOrganizer);
//                organizerUsernameHashMap.put(organizerInfo[1], loadedOrganizer);
            }
        }
    }

    public ArrayList<String> storingOrganizers(){
        ArrayList<String> storedOrganizer = new ArrayList<String>();
        if(organizerHashMap != null && !organizerHashMap.isEmpty()) {
            for(Map.Entry<String, Organizer> o : organizerHashMap.entrySet()) {
                storedOrganizer.add(o.getValue().stringRepresentation() + "\n");
            }
        }
        return storedOrganizer;

    }

}
