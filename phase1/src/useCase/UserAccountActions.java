package useCase;

import entities.Event;
import entities.User;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActions {

    // TODO: We have to change all these things relying on user objects to relying on their string representation

    // create organizer

        // store user
        // id : user1 << put it into serializable



    public boolean addUserContactList(User toMe, User addMe) {
        boolean isUsername = toMe.getContactsList().contains(addMe.getUsername());
        if (isUsername) {
            return false;
        }
        else {
            List<String> toMeContacts = toMe.getContactsList();
            toMeContacts.add(addMe.getUsername());
            toMe.setContactsList(toMeContacts);
            return true;
        }}

    public boolean removeUserContactList(User toMe, User removeMe) {
        boolean isPresent = toMe.getContactsList().contains(removeMe.getUsername());
        if (!isPresent) {
            return false;
        }
        else {
            List<String> toMeContacts = toMe.getContactsList();
            toMeContacts.remove(removeMe.getUsername());
            toMe.setContactsList(toMeContacts);
            return true;
        }}

    public boolean addEventToUser(String event, User user) {
        boolean isPresent = user.getEventList().contains(event);
        if (isPresent) {
            return false;
        }
        else{
            List<String> userEvents = user.getEventList();
            userEvents.add(event);
            user.setEventList(userEvents);
            return true;
        }}

    public boolean removeEventFromUser(String event, User user) {
            boolean isPresent = user.getEventList().contains(event);
            if (!isPresent) {
                return false;
            }
            else{
                List<String> userEvents = user.getEventList();
                userEvents.remove(event);
                user.setEventList(userEvents);
                return true;
            }}


    public String printAllEvents(User user) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < user.getEventList().size(); i++){
           // System.out.println(user.getEventList().get(i));
            String a = user.getEventList().get(i);
            result.append(a).append(' ');
    }
        return result.toString();
}
}
