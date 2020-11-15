package useCase;

import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A use case class which is responsible for validating of the user's username and password match
 * @author Mizna & Jiessie
 * @version 1
 * */

public class LoginActions {

    /**
     * Returns true when the username and password are correct otherwise returns false.
     * @param username the username representing the username
     * @param password the string representing the password
     * @return A boolean if the username and password given are correct
     * */
    public boolean isLogin(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions, AttendeeActions attendeeActions) {
        System.out.println("IsLogin reached");
        System.out.println(organizerActions.returnUsersHashMap());
        System.out.println(speakerActions.returnUsersHashMap());
        System.out.println(attendeeActions.returnUsersHashMap());
        if (organizerActions.returnOrganizersHashMap().containsKey(username) || speakerActions.returnSpeakerHashMap().containsKey(username) || attendeeActions.returnAttendeesHashMap().containsKey(username)){

            User user = userAccountActions.returnUsersHashMap().get(username);
            System.out.println(username);
             if (user.getPassword().equals(password)) {
                 user.setLogin(true);
                 return true;
             }
            user.setLogin(false);
            return false;
        }
        return false;
    }

    private HashMap<String, User> loadHashToHash(HashMap<String, Organizer> loadingHashMap) {
        HashMap<String, User> mainMap;
        //for (Map.Entry<String, Organizer> entry : loadingHashMap.entrySet()) {
            //mainMap.add();
        //}
        return null;
    }
}