package controller;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import gateway.Store;
import useCase.*;
import entities.*;
import presenter.*;
import java.util.Scanner;
// A0000000000000002,sejal,1,,,false,false
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
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        //Instantiate gateway classes
        LoadUpIGateway g = new LoadUp();

        //Instantiate presenter classes
        AccountPresenter accountDisplay = new AccountPresenter();

        //Instantiate use case classes
        MessageActions messageActions = new MessageActions(g);
        EventActions eventActions = new EventActions(g);
        RoomActions roomActions = new RoomActions(g);
        SpeakerActions speakerActions = new SpeakerActions(g);
        OrganizerActions organizerActions = new OrganizerActions(g);
        AttendeeActions attendeeActions = new AttendeeActions(g);
        LogoutActions logoutActions = new LogoutActions();

        //Instantiate controller classes
        LogIn logIn = new LogIn();


        //this loop serves to allow user to return to menu repeatedly
        //loop breaks when user chooses to exit program
        while (true) {
            //login procedure.
            logIn.signUp(organizerActions, speakerActions, attendeeActions);
            accountDisplay.promptUsername();
            String username = scan.nextLine();  // Read user input
            accountDisplay.promptPassword();
            String password = scan.nextLine();  // Read user input

            //String id = logIn.loggingIn(username, password); // evaluate username/password
            String type = logIn.logIn(username, password, organizerActions, speakerActions, attendeeActions);

            // TODO let us logout before login
            if(type.equals("")){
                accountDisplay.failedLogin();
            }
            else {
                accountDisplay.successLogin();

                //instantiate generic menu controller
                MainMenuController menuController;
                // TODO logs out organizer if i want to make a room/ view all events/ choose an option that im not allowed to do
                // TODO option to create a speaker when creating an event?

                if (type.equals("A")) { //indicates attendee
                    // UserController controller = new UserController(eventActions, roomActions, messageActions, attendeeActions, attendeeActions, organizerActions, speakerActions);
                    Attendee user = attendeeActions.returnUsernameHashMap().get(username);
                    accountDisplay = new AttendeeAccountPresenter();
                    AttendeeController attendeeController = new AttendeeController(eventActions, roomActions, messageActions, attendeeActions, organizerActions, speakerActions);
                    menuController = (AttendeeMainMenuController)new AttendeeMainMenuController(user, attendeeController);
                } else if (type.equals("S")) { //indicates speaker
                    // UserController userController = new UserController(eventActions, roomActions, messageActions);
                    Speaker user = speakerActions.returnUsernameHashMap().get(username);
                    accountDisplay = new SpeakerAccountPresenter();
                    SpeakerController speakerController = new SpeakerController(user.getId(), messageActions, eventActions,
                            roomActions,
                            attendeeActions, organizerActions, speakerActions);
                    menuController = (SpeakerMainMenuController) new SpeakerMainMenuController(user, speakerController, eventActions, attendeeActions);
                } else{
                    // UserController controller = new UserController(eventActions, roomActions, messageActions);
                    Organizer user = organizerActions.returnUsernameHashMap().get(username);
                    accountDisplay = new OrganizerAccountPresenter();
                    OrganizerController organizerController = new OrganizerController(user.getId(), messageActions, eventActions,
                            roomActions,
                            attendeeActions, organizerActions, speakerActions);
                    menuController = (OrganizerMainMenuController)new OrganizerMainMenuController(user, organizerController, roomActions, speakerActions, eventActions, organizerActions);
                }

                while (true) {
                    //Menu
                    accountDisplay.printMainMenu();
                    String menuOption = scan.nextLine();
                    if (menuOption.equals("1")) {
                        //logout procedure. will loop back to login procedure if user does not exit
                        Store store = new Store();
                        LogOut logOut = new LogOut(store, messageActions, organizerActions,
                                attendeeActions, roomActions, speakerActions, eventActions, logoutActions);

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
                    } else if (menuOption.equals("7") && (type.equals("A") || type.equals("O"))) {
                        // attendee: cancel enrollment in event
                        // organizer: remove event
                        menuController.option7();
                    } else if (menuOption.equals("8") && (type.equals("A") || type.equals("O"))) {
                        menuController.option8();
                    } else if (menuOption.equals("9") && (type.equals("A") || type.equals("O"))) {
                        menuController.option9();}
                    else if (menuOption.equals("10") && (type.equals("O"))) {
                        menuController.option10();
                    } else {
                        accountDisplay.printMenuError();
                    }
                    accountDisplay.promptReturn();
                    scan.nextLine();
                }
            }
        }
    }

//    public EventActions getEvents(){
//        LoadUpIGateway g = new LoadUp();
//        return new EventActions(g);
//    }
//
//    public OrganizerActions getOrganizers(){
//        LoadUpIGateway g = new LoadUp();
//        return new OrganizerActions(g);
//    }
//
//    public MessageActions getMessages(){
//        LoadUpIGateway g = new LoadUp();
//        return new MessageActions(g);
//    }
//
//    public RoomActions getRooms(){
//        LoadUpIGateway g = new LoadUp();
//        return new RoomActions(g);
//    }
//
//    public SpeakerActions getSpeakers(){
//        LoadUpIGateway g = new LoadUp();
//        return new SpeakerActions(g);
//    }
//
//    public AttendeeActions getAttendees(){
//        LoadUpIGateway g = new LoadUp();
//        return new AttendeeActions(g);
//    }
//
//    public LogoutActions getLogOut(){
//        LoadUpIGateway g = new LoadUp();
//        return new LogoutActions();
//    }
//
//    public UserAccountActions getUserAccountActions(){
//        LoadUpIGateway g = new LoadUp();
//        return new UserAccountActions() {
//            public boolean addUserContactList(String toMe, String addMe) {
//                return false;
//            }
//
//            public boolean removeUserContactList(String toMe, String removeMe) {
//                return false;
//            }
//
//            @Override
//            public boolean addEventToUser(String event, String user) {
//                return false;
//            }
//
//            @Override
//            public boolean removeEventFromUser(String event, String user) {
//                return false;
//            }
//
//            @Override
//            public String returnAllEvents(String user) {
//                return null;
//            }
//
//            @Override
//            public User findUserFromUsername(String username) {
//                return null;
//            }
//
//            @Override
//            public User findUserFromId(String userId) {
//                return null;
//            }
//        };
//    }



}
