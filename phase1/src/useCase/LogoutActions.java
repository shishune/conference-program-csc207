package useCase;

import controller.UserController;
import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A use case class which is responsible for validating of the user's username and password match
 * @author Mizna & Jiessie
 * @version 1
 * */

public class LogoutActions{

    /**
     * Returns true when the username and password are correct otherwise returns false.
     * @param username the username representing the username
     * @return A boolean if the username and password given are correct
     * */
    public void logout(String username, String type, AttendeeActions attendees, OrganizerActions org, SpeakerActions speakers) {
        if (type.equals("O")){
            User user = org.returnUsernameHashMap().get(username);
            user.setLogin(false);
        }
        else if (type.equals("S")){
            User user = speakers.returnUsernameHashMap().get(username);
            user.setLogin(false);
        }
        else{
            User user = attendees.returnUsernameHashMap().get(username);
            user.setLogin(false);
        }
    }
}
