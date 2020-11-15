package controller;

import entities.User;
import useCase.*;


public class LogIn {

    //TODO: Change javadocs
    /**
     * This method is called when the user is logging in after they have inputted a username and password and checks if
     * the username and password are correct, if so returns the user object which was logging in, otherwise returns null
     * @param username A string the user inputs as their username
     * @param password Astring the user inputs as their password
     * @param userActions A UserAccountActions object so it can access the use case class
     * @return The user object of the user if the login was successful otherwise null if given username and password
     * are incorrct.
     */
    public String logIn(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions, AttendeeActions attendeeActions) {
        System.out.println("LOGGING IN");
        LoginActions l = new LoginActions();
        if (l.isLogin(username, password, organizerActions, speakerActions, attendeeActions)) {
            //return userActions.returnUsersHashMap().get(username).getId();
            return organizerActions.returnOrganizersHashMap().get(username).getId() != null
                    ? organizerActions.returnOrganizersHashMap().get(username).getId()
                    : speakerActions.returnSpeakerHashMap().get(username).getId() != null
                    ? speakerActions.returnSpeakerHashMap().get(username).getId()
                    : attendeeActions.returnAttendeesHashMap().get(username).getId() != null
                    ? attendeeActions.returnAttendeesHashMap().get(username).getId()
                    : "";
            // ternary operations
            // dog == animal ? to do if true : to do if false
        }
        return "";
    }
}
