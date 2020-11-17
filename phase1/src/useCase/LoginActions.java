package useCase;

import controller.UserController;
import entities.*;


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
    public String isLogin(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions, AttendeeActions attendeeActions) {

        if (organizerActions.organizerExists(username)){
            Organizer user = organizerActions.returnOrganizersUsernameHashMap().get(username);
            if(user.getPassword().equals(password)) {
                user.setLogin(true);
                return "O";
            }
        }
        else if (speakerActions.speakerExists(username)){
            Speaker user = speakerActions.returnSpeakerUsernameHashMap().get(username);
            if(user.getPassword().equals(password)) {
                user.setLogin(true);
                return "S";
            }
        }
        else if (attendeeActions.attendeeExists(username)){
            Attendee user = attendeeActions.returnAttendeesUsernameHashMap().get(username);
            if(user.getPassword().equals(password)) {
                user.setLogin(true);
                return "A";
            }
        }
        return "";

    }
}