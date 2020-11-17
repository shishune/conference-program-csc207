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
                    Attendee user = attendeeActions.returnAttendeesUsernameHashMap().get(username);
                    accountDisplay = new AttendeeAccountPresenter();
                    AttendeeController attendeeController = new AttendeeController(eventActions, roomActions, messageActions, attendeeActions,
                            organizerActions, speakerActions);
                    menuController = (AttendeeMainMenuController)new AttendeeMainMenuController(user, attendeeController);
                } else if (type.equals("S")) { //indicates speaker
                    Speaker user = speakerActions.returnSpeakerUsernameHashMap().get(username);
                    accountDisplay = new SpeakerAccountPresenter();
                    SpeakerController speakerController = new SpeakerController(user.getId(), messageActions, eventActions,
                            roomActions,
                            speakerActions, organizerActions, attendeeActions);
                    menuController = (SpeakerMainMenuController)new SpeakerMainMenuController(user, speakerController);
                }
                else{
                    Organizer user = organizerActions.returnOrganizersUsernameHashMap().get(username);
                    accountDisplay = new OrganizerAccountPresenter();
                    OrganizerController organizerController = new OrganizerController(user.getId(), messageActions, eventActions,
                            roomActions,
                            attendeeActions, organizerActions, speakerActions);
                    menuController = (OrganizerMainMenuController)new OrganizerMainMenuController(user, organizerController);
                }

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
                    System.out.println("Running Option 6");
                    menuController.option6();
                    System.out.println("Finished Option 6");
                } else if (menuOption.equals("7") && (type.equals("A") || type.equals("O"))) {
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
                else if (menuOption.equals("8") && (type.equals("A") || type.equals("O"))){
                    menuController.option8();
                }
                else {
                    accountDisplay.printMenuError();
                }
                // TODO: wait for them to choose before auto log out (cycling to top of while loop)
                accountDisplay.promptReturn();

            }
        }
    }


}
