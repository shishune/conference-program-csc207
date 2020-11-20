package controller;

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
        LoginActions l = new LoginActions();
        String type = l.isLogin(username, password, organizerActions, speakerActions, attendeeActions);
        return type;
    }


    public void signUp(OrganizerActions organizerActions, SpeakerActions speakerActions,
                             AttendeeActions attendeeActions) {

        while (true){

            accountDisplay.printSignUpMenu();
            String loginOption = scan.nextLine();
            if (loginOption.equals("x") || loginOption.equals("X")) {
            accountDisplay.promptUsername();
            String username = scan.nextLine();  // Read user input

            accountDisplay.promptPassword();
            String password = scan.nextLine();  // Read user input

                accountDisplay.printUserTypeMenu();
                if(signUpCheck(username, password, organizerActions, speakerActions, attendeeActions)){
                    accountDisplay.successSignUp();
                    break;
                }
            }
            else{
                break;
            }
        }

    }
    //TODO: WHAT IF THEY ENTER SOMETHING THATS NOT 123?
    private boolean signUpCheck(String username, String password, OrganizerActions organizerActions, SpeakerActions speakerActions,
                           AttendeeActions attendeeActions) {
        String userType = scan.nextLine();

        if (userType.equals("1")) {
            if (!organizerActions.organizerExists(username)) {
                organizerActions.createOrganizer(username, password, false);
                return true;
            }
            else{
                accountDisplay.failedUsernameExists();
                return false;
            }
        } else if (userType.equals("2")) {
            if (!speakerActions.speakerExists(username)) {
                speakerActions.createSpeaker(username, password, new ArrayList<String>(), new ArrayList<String>(), false);
                return true;
            }
            else{
                accountDisplay.failedUsernameExists();
                return false;
            }
        } else if (userType.equals("3")) {
            if (!attendeeActions.attendeeExists(username)) {
                attendeeActions.createAttendee(username, password, new ArrayList<String>(), new ArrayList<String>(), false);
                return true;
            }
            else{
                accountDisplay.failedUsernameExists();
                return false;
            }
        }
        else{
            accountDisplay.printTypingError();
            return false;
        }

    }
}

