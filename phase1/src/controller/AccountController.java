package controller;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import gateway.Store;
import useCase.*;
import entities.*;
import presenter.*;
import java.util.Scanner;

/**
 * A master controller class to take in user input and respond using other controllers and presenters.
 * This is what is run in the main method.
 * @author Cynthia
 * @version 1
 * */
public class AccountController {

    /**
     * Interacts with user to prompt menu item choice and decides which presenter and controller
     * methods to use to respond
     */
    public void run(){
        //Instantiate gateway classes
        LoadUpIGateway g = new LoadUp();

        //Instantiate presenter classes
        AccountPresenter accountDisplay = new AccountPresenter();

        //Instantiate use case classes
        MessageActions messageActions = new MessageActions(g);
        EventActions eventActions = new EventActions(g);
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions(g);
        SpeakerActions speakerActions = new SpeakerActions(g);
        OrganizerActions organizerActions = new OrganizerActions(g);
        AttendeeActions attendeeActions = new AttendeeActions(g);
        LogoutActions logoutActions = new LogoutActions();

        //Instantiate controller classes
        LogIn logIn = new LogIn();
        UserController controller = new UserController(userAccountActions, eventActions, roomActions, messageActions, attendeeActions);

        //this loop serves to allow user to return to menu repeatedly
        //loop breaks when user chooses to exit program
        while (true) {
            //login procedure.
            Scanner scan = new Scanner(System.in);  // Create a Scanner object
            accountDisplay.promptUsername();
            String username = scan.nextLine();  // Read user input
            accountDisplay.promptPassword();
            String password = scan.nextLine();  // Read user input
            //String id = logIn.loggingIn(username, password); // evaluate username/password
            String userID = logIn.logIn(username, password, userAccountActions);

            // TODO let us logout before login
            if(userID.equals("")){
                System.out.println("Incorrect username or password");
            }else {
                System.out.println("good password");
                User user = userAccountActions.returnUsersHashMap().get(userID);
                //instantiate generic menu controller
                MainMenuController menuController = new MainMenuController(user, controller);

                if (user.getIsOrganizer()) { // indicates organizer
                    accountDisplay = new OrganizerAccountPresenter();
                    OrganizerController organizerController = new OrganizerController(user.getId(), messageActions, eventActions,
                            userAccountActions, roomActions,
                            speakerActions, organizerActions, attendeeActions);
                    menuController = new OrganizerMainMenuController(user, controller, organizerController);
                } else if (user.getId().charAt(0) == 'A') { //indicates attendee
                    accountDisplay = new AttendeeAccountPresenter();
                    //AttendeeController attendeeController = new AttendeeController(userAccountActions, eventActions, roomActions, messageActions, attendeeActions);
                    menuController = new AttendeeMainMenuController(user, controller);
                } else if (user.getId().charAt(0) == 'S') { //indicates speaker
                    accountDisplay = new SpeakerAccountPresenter();
                    SpeakerController speakerController = new SpeakerController(user.getId(), messageActions, eventActions,
                            userAccountActions, roomActions,
                            speakerActions, organizerActions, attendeeActions);
                    menuController = new SpeakerMainMenuController(user, controller, speakerController);
                }

                //Menu
                accountDisplay.printMainMenu();
                String menuOption = scan.nextLine();
                if (menuOption.equals("1")) {
                    //logout procedure. will loop back to login procedure if user does not exit
                    Store store = new Store();
                    LogOut logOut = new LogOut(store, userAccountActions, messageActions, organizerActions,
                            attendeeActions, roomActions, speakerActions, eventActions, logoutActions);
                    logOut.loggingOut(username);
                    accountDisplay.successLogout();
                    String choice = scan.nextLine();
                    if (choice.equals('x') || choice.equals('X')) {
                        logOut.exit();
                        break;
                    }
                } else if (menuOption.equals("2")) {
                    // send message. for attendee there is just one option, for organizer/speaker there
                    // are several options
                    menuController.option2();
                } else if (menuOption.equals("3")) { //view all messages
                    menuController.option3();
                } else if (menuOption.equals("4")) { //add contact
                    menuController.option4();
                } else if (menuOption.equals("5")) { //view all contacts
                    menuController.option5();
                } else if (menuOption.equals("6")) {
                    // attendee: sign up for event
                    // organizer: add event
                    // speaker: see schedule of given talks
                    menuController.option6();
                } else if (menuOption.equals("7") && (user.getId().charAt(0) == 'A' || user.getIsOrganizer())) {
                    // attendee: cancel enrollment in event
                    // organizer: remove event

                    menuController.option7();
                }
                //            else if(menuOption.equals("8") && (user.getId().charAt(0)=='A'||user.getIsOrganizer())){
                //                // both: view all events
                //
                //                menuController.option8();
                //            }
                //            else if(menuOption.equals("9") && (user.getId().charAt(0)=='A'||user.getIsOrganizer())){
                //                // organizer: add room
                //                //attendee: view own schedule of events
                //
                //                menuController.option9();
                //            }
                else {
                    accountDisplay.printMenuError();
                }
                accountDisplay.promptReturn();

            }
        }
    }


}
