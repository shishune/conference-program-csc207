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
    public boolean isLogin(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions, AttendeeActions attendeeActions, UserController controller) {
        System.out.println("IsLogin reached");
        System.out.println(organizerActions.returnOrganizersHashMap());
        System.out.println(speakerActions.returnSpeakerUsernameHashMap());
        System.out.println(attendeeActions.returnAttendeesHashMap());
        if(controller.returnUsernameHashMap().containsKey(username)){
            //the problem here is that the master hashmap is empty but idk why cuz it isn't empty before. i think the organizer entity hashmap thing gets erased idk why and im not sure
            User user = controller.returnUsernameHashMap().get(username);
        //if (organizerActions.returnOrganizersHashMap().containsKey(username) || speakerActions.returnSpeakerIDHashMap().containsKey(username) || attendeeActions.returnAttendeesHashMap().containsKey(username)){
//            User user = organizerActions.returnOrganizersHashMap().get(username) != null
//                    ? organizerActions.returnOrganizersHashMap().get(username)
//                    : speakerActions.returnSpeakerUsernameHashMap().get(username) != null
//                    ? speakerActions.returnSpeakerUsernameHashMap().get(username)
//                    : attendeeActions.returnAttendeesHashMap().get(username) != null
//                    ? attendeeActions.returnAttendeesHashMap().get(username)
//                    : null;
            if(user.getPassword().equals(password)) {
                user.setLogin(true);
                return true;
            }
            user.setLogin(false);
            //System.out.println(username);
             //if (user.getPassword().equals(password)) {
                 //user.setLogin(true);
                 //return true;
             //}
            //user.setLogin(false);
            return false;
        }
        return false;
    }
}