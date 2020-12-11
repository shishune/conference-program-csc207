package controllers;

import gateways.LoadUp;
import gateways.LoadUpIGateway;
import gateways.Store;
import presenters.AccountPresenter;
import useCases.RoomActions;

import java.util.Scanner;


/**
 * A master controller class to take in user input and respond using other controllers and presenters.
 * This is what is run in the main method.
 * @author multiple
 * @version 1
 * */
public class AccountController {
    /**
     * Interacts with user to prompt menu item choice and decides which presenter and controller
     * methods to use to respond
     */

    public void run(){
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        //Instantiate gateway classes
        LoadUpIGateway g = new LoadUp();

        //Instantiate presenter classes
        presenters.AccountPresenter accountDisplay = new AccountPresenter();

        //Instantiate use case classes
        useCases.MessageActions messageActions = new useCases.MessageActions(g);
        useCases.EventActions eventActions = new useCases.EventActions(g);
        useCases.RoomActions roomActions = new RoomActions(g);
        useCases.SpeakerActions speakerActions = new useCases.SpeakerActions(g);
        useCases.OrganizerActions organizerActions = new useCases.OrganizerActions(g);
        useCases.AttendeeActions attendeeActions = new useCases.AttendeeActions(g);
        useCases.ConferenceActions conferenceActions = new useCases.ConferenceActions(g);
        useCases.LogoutActions logoutActions = new useCases.LogoutActions();

        //Instantiate use case parameter objects
        parameterObjects.AccountActions accountActions = new parameterObjects.AccountActions(attendeeActions, speakerActions, organizerActions);
        parameterObjects.EventSystemActions eventSystemActions = new parameterObjects.EventSystemActions(conferenceActions, eventActions, messageActions, roomActions);

        controllers.UserController userController = new controllers.UserController(eventSystemActions, accountActions, 'u');

        //Instantiate controller classes
        controllers.LogIn logIn = new LogIn();


        //this loop serves to allow user to return to menu repeatedly
        //loop breaks when user chooses to exit program
        while (true) {
            //login procedure.
            logIn.signUp(userController, accountActions);
            accountDisplay.promptUsername();
            String username = scan.nextLine();  // Read user input
            accountDisplay.promptPassword();
            String password = scan.nextLine();  // Read user input

            //String id = logIn.loggingIn(username, password); // evaluate username/password
            String type = logIn.logIn(username, password, accountActions);

            if(type.equals("")){
                accountDisplay.failedLogin();
            }
            else {
                accountDisplay.successLogin();

                //instantiate generic menu controller
                MainMenuController menuController;

                if (type.equals("A")) { //indicates attendee

                    String user = attendeeActions.returnUsernameHashMap().get(username).getId();
                    boolean isVIP = attendeeActions.findUserFromUsername(username).getIsVIP();

                    accountDisplay = new presenters.AttendeeAccountPresenter();
                    controllers.AttendeeController attendeeController = new AttendeeController(eventSystemActions, accountActions);
                    menuController = (controllers.AttendeeMainMenuController)new AttendeeMainMenuController(user, attendeeController,
                            roomActions, speakerActions, conferenceActions);


                }
                else if (type.equals("S")) { //indicates speaker
                    String user = speakerActions.returnUsernameHashMap().get(username).getId();
                    accountDisplay = new presenters.SpeakerAccountPresenter();
                    controllers.SpeakerController speakerController = new SpeakerController(user, eventSystemActions, accountActions);
                    menuController = (controllers.SpeakerMainMenuController) new SpeakerMainMenuController(user, speakerController,
                            eventActions, attendeeActions, roomActions, speakerActions, conferenceActions);
                }
                else{ //indicates organizer
                    String user = organizerActions.returnUsernameHashMap().get(username).getId();
                    accountDisplay = new presenters.OrganizerAccountPresenter();
                    controllers.OrganizerController organizerController = new OrganizerController(user, eventSystemActions, accountActions);
                    menuController = (controllers.OrganizerMainMenuController)new OrganizerMainMenuController(user, organizerController, roomActions, eventActions, conferenceActions, accountActions);
                }

                while (true) {
                    //Menu
                    accountDisplay.printMainMenu();
                    String menuOption = scan.nextLine();
                    if (menuOption.equals("1")) {
                        //logout procedure. will loop back to login procedure if user does not exit
                        Store store = new Store();
                        controllers.LogOut logOut = new LogOut(store, eventSystemActions, accountActions, logoutActions);

                        logOut.loggingOut(username, type);

                        accountDisplay.successLogout();
                        String choice = scan.nextLine();
                        if (choice.equals("x") || choice.equals("X")) {
                            logOut.exit();
                        }
                        break;
                    } else if (menuOption.equals("2")) {
                        // send message. for attendee there is just one option, for organizer/speaker there
                        // are several options
                        menuController.option2SendMessage();
                    } else if (menuOption.equals("3")) { //view all messages
                        menuController.option3ViewAllMessages();
                    } else if (menuOption.equals("4")) { //add contact
                        menuController.option4AddContact();
                    } else if (menuOption.equals("5")) { //view all contacts
                        menuController.option5ViewAllContacts();
                    } else if (menuOption.equals("6")) {
                        // attendee: sign up for event
                        // organizer: add event
                        // speaker: see schedule of given talks
                        menuController.option6EventSignupCreateView();
                    } else if (menuOption.equals("7") && (type.equals("A") || type.equals("O"))) {
                        // attendee: cancel enrollment in event
                        // organizer: remove event
                        menuController.option7Cancel();
                    } else if (menuOption.equals("8") && (type.equals("A") || type.equals("O"))) {
                        menuController.option8ViewAllEvents();
                    } else if (menuOption.equals("9") && (type.equals("A") || type.equals("O"))) {
                        menuController.option9ScheduleOrRoom();}
                    else if (menuOption.equals("10") && (type.equals("O") || type.equals("A"))) {
                        menuController.option10AddOrViewEvents();
                    } else if (menuOption.equals("11") && (type.equals("O") || type.equals("A"))) {
                        menuController.option11VIPOrConferences();
                    } else if (menuOption.equals("12") && (type.equals("O") || type.equals("A"))) {
                        menuController.option12Conference();
                    }  else if (menuOption.equals("13")) {
                        menuController.option13ChangeCapacity();
                    }
                    else if (menuOption.equals("14")&& (type.equals("O"))) {
                        //view all conferences
                        menuController.option14ViewStatistics();
                    }

                    else {
                        accountDisplay.printMenuError();
                    }
                    accountDisplay.promptReturn();
                    scan.nextLine();
                }
            }
        }
    }

}
