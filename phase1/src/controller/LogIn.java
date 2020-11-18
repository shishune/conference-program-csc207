package controller;

import entities.User;
import useCase.*;
import presenter.AccountPresenter;
import java.util.Scanner;
import java.util.ArrayList;

public class LogIn {
    private Scanner scan = new Scanner(System.in);  // Create a Scanner object
    private AccountPresenter accountDisplay = new AccountPresenter();
    private UserController controller;
    //TODO: Change javadocs
    /**
     * This method is called when the user is logging in after they have inputted a username and password and checks if
     * the username and password are correct, if so returns the user object which was logging in, otherwise returns null
     * @param username A string the user inputs as their username
     * @param password A string the user inputs as their password
     * @return The user object of the user if the login was successful otherwise null if given username and password
     * are incorrect.
     */
    public String logIn(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions, AttendeeActions attendeeActions) {
        System.out.println("LOGGING IN");
        LoginActions l = new LoginActions();
        String type = l.isLogin(username, password, organizerActions, speakerActions, attendeeActions);
        return type;
    }


    // is this stuff suppose to go in a login presenter? sorry if this was temporary and u were meaning to change it tho
    public void signUp(OrganizerActions organizerActions, SpeakerActions speakerActions,
                             AttendeeActions attendeeActions) {

        while (true){

            accountDisplay.printSignUpMenu();
            String loginOption = scan.nextLine();
            if (loginOption.equals("x") || loginOption.equals("X")) {
            accountDisplay.promptUsername();
            String username = scan.nextLine();  // Read user input
                if (controller != null) {
                    if (controller.returnUsernameHashMap().containsKey(username)) {
                        accountDisplay.failedUsernameExists();
                    }
                }
            accountDisplay.promptPassword();
            String password = scan.nextLine();  // Read user input

                accountDisplay.printUserTypeMenu();
                if(signUpCheck(username, password, organizerActions, speakerActions, attendeeActions)){
                    // i think its here we have to add the create user? (for whatever type user they are and that will automatically add them to the hashmap)
                    // TODO: it's not saving the attendee information, but it's working for organizer
                    accountDisplay.successSignUp();
                    break;
                }
            }
            else{
                break;
            }
        }

    }
    private boolean signUpCheck(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions,
                           AttendeeActions attendeeActions){
        OrganizerController org = new OrganizerController();
        while (true) {
            String userType = scan.nextLine();
            if (userType.equals("1")) {
                if (!organizerActions.organizerExists(username)) {
                    organizerActions.createOrganizer(username, password, false);
                    return true;
                }
                break;
            }
            else if (userType.equals("2")){
                if (!speakerActions.speakerExists(username)){
                    speakerActions.createSpeaker(username, password, new ArrayList<String>(), new ArrayList<String>(), false);
                    return true;
                }
                break;
            }
            else if (userType.equals("3")){
                if (!attendeeActions.attendeeExists(username)){
                    attendeeActions.createAttendee(username, password, new ArrayList<String>(), new ArrayList<String>(), false);
                    return true;
                }
            }
            else{
                accountDisplay.failedInvalidMenuOption();
            }
        }
        accountDisplay.failedUsernameExists();
        return false;
    }
}
