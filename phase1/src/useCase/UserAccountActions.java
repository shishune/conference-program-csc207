package useCase;

import entities.User;

import java.util.List;
import java.util.HashMap;
/**
 * A use case class that stores a hashmap of users and can add and remove users from the hashmap.
 * Parent class to AttendeeActions, OrganizationActions and SpeakerActions. This class collaborates with user,
 * message, event and conference entity.
 * @author multiple
 * @version 1
 */
public class UserAccountActions {
    // hashmap the contains all the user objects; key is userID, value is userObject

    protected HashMap<String, User> usersHashMap = new HashMap<String, User>();

    public HashMap<String, User> returnUsersHashMap(){
        return usersHashMap;
    }

    /**
     * Adds an userId to existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     * @param addMe the user to be added
     * @return void
     * */
    public void addUserIdToHashMap(User addMe) {

        if (usersHashMap.containsKey(addMe.getId())) {
            usersHashMap.put(addMe.getId(), addMe);
        }

    }


    /**
     * Adds an username to existing hashmap of usernames.
     * The key is the username, the value is an instance of the user object.
     * @param addMe the user to be added
     * @return void
     * */
    public void addUsernameToHashMap(User addMe){

        if (usersHashMap.containsKey(addMe.getUsername())){
            usersHashMap.put(addMe.getUsername(), addMe);
        }

    }

    /**
     * Removes an userId from existing hashmap of userId's.
     * The key is the userId, the value is an instance of the user object.
     * @param removeMe the user to be removed
     * @return true if user is removed successfully, false if it has not been removed
     * */
    public boolean removeUserIdFromHashMap(User removeMe){
        if (usersHashMap.containsKey(removeMe.getId())){
            usersHashMap.remove(removeMe.getId(), removeMe);
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

    public boolean removeUsernameFromHashMap(User removeMe){
        if (usersHashMap.containsKey(removeMe.getUsername())){
            usersHashMap.remove(removeMe.getUsername(), removeMe);
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

    public boolean addUserContactList(String toMe, String addMe, HashMap<String, User> usersHashMap) {
        User user = usersHashMap.get(toMe);
        User userOne = usersHashMap.get(addMe);
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
        User user = usersHashMap.get(toMe);
        User userOne = usersHashMap.get(removeMe);
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
        User userOne = usersHashMap.get(user);
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
        User userOne = usersHashMap.get(user);
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
     * @param user the user who's eventList is printed
     * @return string of all the events a user is attending
     * */

    public String returnAllEvents(String user) {
        User userOne = usersHashMap.get(user);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < userOne.getEventList().size(); i++){
           // System.out.println(user.getEventList().get(i));
            String a = userOne.getEventList().get(i);
            result.append(a).append(' ');
    }
        return result.toString();
}
    /**
     * Finds an user from a given username
     * @param username the username given
     * @return user object from hashmap of user objects
     * */
    public User findUserFromUsername(String username){
        return usersHashMap.get(username);
    }

    /**
     * Finds an user from a given userId
     * @param userId the userId given
     * @return user object from hashmap of user objects
     * */
    public User findUserFromId(String userId){
        return usersHashMap.get(userId);
    }

}
