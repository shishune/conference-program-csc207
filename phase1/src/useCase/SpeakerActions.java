package useCase;

import entities.Attendee;
import entities.Organizer;
import entities.Speaker;
import entities.User;
import gateway.LoadUpIGateway;

import java.util.*;

public class SpeakerActions extends UserAccountActions {
    private LoadUpIGateway loader;
    private ArrayList<String> speakerLoadUpList = new ArrayList<String>();

    private HashMap<String, Speaker> speakerID = new HashMap<String, Speaker>();
    private HashMap<String, Speaker> speakerUsername = new HashMap<String, Speaker>();

    private ArrayList<String> speakers = new ArrayList<String>();
    public ArrayList<String> storedSpeaker = new ArrayList<String>();

    public SpeakerActions(LoadUpIGateway loader) {
        // with message ID as key and message object as value
        this.loader = loader;
        getAllSpeakers(loader); // gets all messages from message.csv
        addSpeakerToHashMap(); // adds those messages to a hashmap of all messages from the csv
    }
    // why does this return user instead of speaker type?
    public Speaker createSpeaker(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Speaker userSpeaker = new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userSpeaker);
        loadSpeaker(userSpeaker);
        return userSpeaker;
    }

    public Speaker createSpeaker(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID(loader);
        String userId = "S" + generateId.generateId();
        Speaker userSpeaker = new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
        //addUserIdToHashMap(userSpeaker);
        //addUsernameToHashMap(userSpeaker);
        //speakerUsername.put(userId, userSpeaker);
        //speakerID.put(username, userSpeaker);
        loadSpeaker(userSpeaker);
        return userSpeaker;
    }

    /**
     * Returns whether speaker is in the database
     * @param username the username of user
     * @return true iff speaker exists
     */
    public boolean speakerExists(String username){
        return speakerUsername.containsKey(username);
    }
    /*
    /** Load new speakers into HashMap of new speakers **/
    public void loadSpeaker(Speaker newSpeaker){
        speakerID.put(newSpeaker.getId(), newSpeaker);
        speakerUsername.put(newSpeaker.getUsername(), newSpeaker);
    }

    public HashMap<String, Speaker> returnIDHashMap() {
        return speakerID;
    }

    public HashMap<String, Speaker> returnUsernameHashMap() {
        return speakerUsername;
    }

    /** gets list of messages from the IGateway **/
    private void getAllSpeakers(LoadUpIGateway loader) {
        speakers = loader.getSpeakersList();
    }

    /** Adds messages loaded from the csv to <messages> **/
    private void addLoadedToHashMap() {
        if (speakerLoadUpList != null) {
            for(String speakerString : speakerLoadUpList) {
                String[] speakerInfo = speakerString.split(",");
                List<String> loadContactsList = Arrays.asList(speakerInfo[3].split("%%"));
                List<String> loadEventsList = Arrays.asList(speakerInfo[4].split("%%"));
                boolean loadIsLogin = Boolean.parseBoolean(speakerInfo[5]);
                boolean loadIsOrganizer = Boolean.parseBoolean(speakerInfo[6]);
                Speaker loadedSpeaker = new Speaker(speakerInfo[0], speakerInfo[1], speakerInfo[2], loadContactsList, loadEventsList, loadIsLogin, loadIsOrganizer);
                //Message loadedMessage = createMessage(messageInfo[0], messageInfo[1], messageInfo[2], messageInfo[3], messageInfo[4]);
                loadSpeaker(loadedSpeaker);
            }
        }
    }


    /**
     * Adds an userId to existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     *
     * @param addMe the user to be added
     */
    private void addUserIdToHashMap(Speaker addMe) {
        if (speakerID.containsKey(addMe.getId())) {
            speakerID.put(addMe.getId(), addMe);
        }
    }

    /**
     * Adds an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     *
     * @param addMe the user to be added
     */
    private void addUsernameToHashMap(Speaker addMe) {

        if (speakerUsername.containsKey(addMe.getUsername())) {
            speakerUsername.put(addMe.getUsername(), addMe);
        }
    }


    /**
     * Removes an userId from existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     *
     * @param removeMe the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     */
    private boolean removeUserIdFromHashMap(Speaker removeMe) {
        if (speakerID.containsKey(removeMe.getId())) {
            speakerID.remove(removeMe.getId(), removeMe);
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

    private boolean removeUsernameFromHashMap(Speaker removeMe) {
        if (speakerUsername.containsKey(removeMe.getUsername())) {
            speakerUsername.remove(removeMe.getUsername(), removeMe);
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
    public boolean addUserContactList(String toMe, String addMe, HashMap<String, User> userUsernameHashMap) {
        User user = userUsernameHashMap.get(toMe);
        User userOne = userUsernameHashMap.get(addMe);
        if(userOne == null || userOne.getId() == null) {
            return false;
        }
        boolean isId = user.getContactsList().contains(userOne.getId());
        if (/*userOne.getIsOrganizer() || */user.getId() == userOne.getId() || isId) {
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
     * @param removeMe the username of the user to be removed
     * @param toMe     the username of user who's contact list is updated
     * @return true if user is removed successfully, false if not
     */

    public boolean removeUserContactList(String toMe, String removeMe) {
        Speaker user = speakerUsername.get(toMe);
        User userOne = speakerUsername.get(removeMe);
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
        User userOne = speakerUsername.get(user);
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
        Speaker userOne = speakerUsername.get(user);
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
        Speaker userOne = speakerUsername.get(user);
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

    public Speaker findUserFromUsername(String username) {
        return speakerUsername.get(username);
    }


    /**
     * Finds an user from a given userId
     *
     * @param userId the userId given
     * @return user object from hashmap of user objects
     */
    public Speaker findUserFromId(String userId) {
        return speakerID.get(userId);
    }


    private void addSpeakerToHashMap() {
        if (speakers != null  && !speakers.isEmpty()) {

            for (String speakerString : speakers) {
                String[] speakerInfo = speakerString.split(",");
                ArrayList<String> eventList = new ArrayList<String>();
                ArrayList<String> contactList = new ArrayList<String>();
                String[] events = speakerInfo[4].split("%%");
                String[] contacts = speakerInfo[3].split("%%");
                for (String e : events) {
                    if (!e.equals("")) {
                        eventList.add(e);
                    }
                }
                for (String c : contacts) {
                    if (!c.equals("")){
                        contactList.add(c);
                    }
                }
                Speaker loadedSpeaker = new Speaker(speakerInfo[0], speakerInfo[1], speakerInfo[2], contactList,
                        eventList, Boolean.parseBoolean(speakerInfo[5]), Boolean.parseBoolean(speakerInfo[6]));
//                speakerID.put(speakerInfo[0], loadedSpeaker);
//                speakerUsername.put(speakerInfo[1], loadedSpeaker);
                loadSpeaker(loadedSpeaker);
            }


        }
    }

    public ArrayList<String> storeSpeakers() {
        ArrayList<String> storedSpeaker = new ArrayList<String>();
        if(speakerID != null && !speakerID.isEmpty()) {
            for (Map.Entry<String, Speaker> o : speakerID.entrySet()) {
                storedSpeaker.add(o.getValue().stringRepresentation() + "\n");
            }
        }
        return storedSpeaker;
    }

    public ArrayList<String> getSpeakerIds() {
        ArrayList<String> storedS = new ArrayList<String>();
        if (speakerID != null && !speakerID.isEmpty()) {
            for (Map.Entry<String, Speaker> o : speakerID.entrySet()) {
                storedS.add(o.getValue().getId() + "\n");
            }
        }
        return storedS;
    }



}
