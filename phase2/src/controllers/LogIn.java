package controllers;

import presenters.AccountPresenter;
import useCases.LoginActions;

import java.util.Scanner;
import java.util.ArrayList;

public class LogIn {
    private Scanner scan = new Scanner(System.in);  // Create a Scanner object
    private AccountPresenter accountDisplay = new AccountPresenter();
    private UserController controller;
    /**
     * This method is called when the user is logging in after they have inputted a username and password and checks if
     * the username and password are correct, if so returns the user object which was logging in, otherwise returns null
     * @param username A string the user inputs as their username
     * @param password A string the user inputs as their password
     * @return The user object of the user if the login was successful otherwise null if given username and password
     * are incorrect.
     */
    public String logIn(String username, String password, useCases.OrganizerActions organizerActions, useCases.SpeakerActions speakerActions, useCases.AttendeeActions attendeeActions) {
        useCases.LoginActions l = new LoginActions();
        String type = l.isLogin(username, password, organizerActions, speakerActions, attendeeActions);
        return type;
    }

    /**
     * This method is called when the user enter 'x' to sigh up. It reads the username and password the user inputted.
     * @param userController controller responsible for all users
     * @param organizerActions the use case responsible for organizers
     * @param speakerActions the use case responsible for speakers
     * @param attendeeActions the use case responsible for attendees
     */
    public void signUp(controllers.UserController userController, useCases.OrganizerActions organizerActions, useCases.SpeakerActions speakerActions,
                       useCases.AttendeeActions attendeeActions) {

        while (true){

            accountDisplay.printSignUpMenu();
            String loginOption = scan.nextLine();
            if (loginOption.equals("x") || loginOption.equals("X")) {

            accountDisplay.promptUsername();
            String username = scan.nextLine();  // Read user input

            accountDisplay.promptPassword();
            String password = scan.nextLine();  // Read user input


            accountDisplay.printUserTypeMenu();

                if(signUpCheck(username, password, userController, organizerActions, speakerActions, attendeeActions)){
                    accountDisplay.successSignUp();
                    break;
                }
            }
            else{
                break;
            }
        }

    }

    /**
     * Checks if username is unique
     * @param username A string the user inputs as their username
     * @param password A string the user inputs as their password
     * @param organizerActions the use case responsible for organizers
     * @param speakerActions the use case responsible for speakers
     * @param attendeeActions the use case responsible for attendees
     * @return true if the username is unique otherwise return false
     */
    private boolean signUpCheck(String username, String password, controllers.UserController userController,
                                useCases.OrganizerActions organizerActions, useCases.SpeakerActions speakerActions,
                                useCases.AttendeeActions attendeeActions) {
        String userType = scan.nextLine();
        if (userController.usernameExists(username)){
            accountDisplay.failedUsernameExists();
            return false;
        } else {
            // note: implements factory pattern
            if (userType.equals("1")) {
                organizerActions.createOrganizer(username, password);
                return true;
            } else if (userType.equals("2")) {
                speakerActions.createSpeaker(username, password, new ArrayList<String>(), new ArrayList<String>(), false);
                return true;

            } else if (userType.equals("3")) {
                accountDisplay.VIPStatusPrompt();
                boolean VIPStatus;
                String responseInput = scan.nextLine();

                VIPStatus = responseInput.equals("VIP") || responseInput.equals("vip");

                if (!attendeeActions.attendeeExists(username)) {
                    attendeeActions.createAttendee(username, password, new ArrayList<String>(), new ArrayList<String>(),
                            new ArrayList<String>(), false, VIPStatus);
                    return true;
                }
                else{
                    accountDisplay.failedUsernameExists();
                    return false;
                }
            } else{
                accountDisplay.printTypingError();
                return false;
            }
        }


    }
}

