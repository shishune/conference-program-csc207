package useCase;

import entities.User;

import java.util.List;
import java.util.HashMap;

public class UserAccountActions {
    // hashmap the contains all the user objects; key is userID, value is userObject
    public HashMap<String, User> usersHashMap;

    public HashMap<String, User> returnUsersHashMap(){
        return usersHashMap;
    }

    public void addUserIdToHashMap(User addMe){

        if (usersHashMap.containsKey(addMe.getId())){
            usersHashMap.put(addMe.getId(), addMe);
        }

    }

    public void addUsernameToHashMap(User addMe){

        if (usersHashMap.containsKey(addMe.getUsername())){
            usersHashMap.put(addMe.getUsername(), addMe);
        }

    }

    public boolean removeUserIdFromHashMap(User removeMe){
        if (usersHashMap.containsKey(removeMe.getId())){
            usersHashMap.remove(removeMe.getId(), removeMe);
            return true;
        }
        return false;
    }

    public boolean removeUsernameFromHashMap(User removeMe){
        if (usersHashMap.containsKey(removeMe.getUsername())){
            usersHashMap.remove(removeMe.getUsername(), removeMe);
            return true;
        }
        return false;
    }

    public boolean addUserContactList(String toMe, String addMe) {
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

    public void removeEventFromUser(String event, String user) {
        User userOne = usersHashMap.get(user);
            boolean isPresent = userOne.getEventList().contains(event);
            if (isPresent) {
                List<String> userEvents = userOne.getEventList();
                userEvents.remove(event);
                userOne.setEventList(userEvents);
            }}


    public String printAllEvents(String user) {
        User userOne = usersHashMap.get(user);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < userOne.getEventList().size(); i++){
           // System.out.println(user.getEventList().get(i));
            String a = userOne.getEventList().get(i);
            result.append(a).append(' ');
    }
        return result.toString();
}
    public User findUserFromUsername(String username){
        return usersHashMap.get(username);
    }

    public User findUserFromId(String userId){
        return usersHashMap.get(userId);
    }

    public HashMap<String, User> getUsersHashMap(){
        return usersHashMap;
    }
}
