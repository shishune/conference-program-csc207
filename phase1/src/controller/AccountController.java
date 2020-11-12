package controller;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import gateway.Store;
import useCase.*;
import entities.*;
import presenter.*;
import java.util.Scanner;

/**
 * Navigates account; takes in user input
 */
public class AccountController {

    //just an idea to show how controllers interact with presenters; subject to change
    public void run(){
        LoadUpIGateway g = new LoadUp();

        AccountPresenter accountDisplay = new AccountPresenter();
        MessagePresenter messageDisplay = new MessagePresenter();
        EventPresenter eventDisplay = new EventPresenter();

        MessageActions messageActions = new MessageActions(g);
        EventActions eventActions = new EventActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions();
        SpeakerActions speakerActions = new SpeakerActions();
        OrganizerActions organizerActions = new OrganizerActions(g);
        AttendeeActions attendeeActions = new AttendeeActions(g);

        LogIn logIn = new LogIn();

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
            User user = logIn.logIn(username, password, userAccountActions);

            if (user.getIsOrganizer()){ // indicates organizer
                accountDisplay = new OrganizerAccountPresenter();
                messageDisplay = new OrganizerMessagePresenter();
            }
            else if (user.getId().charAt(0)=='A'){ //indicates attendee
                accountDisplay = new AttendeeAccountPresenter();
                messageDisplay = new AttendeeMessagePresenter();
            }
            else if (user.getId().charAt(0)=='S'){ //indicates speaker
                accountDisplay = new SpeakerAccountPresenter();
                messageDisplay = new SpeakerMessagePresenter();
            }

            //Menu
            accountDisplay.printMainMenu();
            String menuOption = scan.nextLine();
            if (menuOption.equals("1")){
                //logout procedure. will loop back to login procedure if user does not exit
                Store store = new Store();
                LogOut logOut = new LogOut(store, messageActions, organizerActions,
                        attendeeActions, roomActions, speakerActions, eventActions);
                logOut.loggingOut();
                accountDisplay.successLogout();
                String choice = scan.nextLine();
                if (choice.equals('x')|| choice.equals('X')){
                    logOut.exit();
                    break;
                }
            }
            else if(menuOption.equals("2")){
                //do something
            }
            else if(menuOption.equals("3")){
                //do something
            }
            else if(menuOption.equals("4")){
                //do something
            }
            else if(menuOption.equals("5")){
                //do something
            }
            else if(menuOption.equals("5")){
                //do something
            }
            else if(menuOption.equals("6")){
                //do something
            }
            else if(menuOption.equals("7")){
                //do something
            }
            else{
                accountDisplay.printMenuError();
            }
            accountDisplay.promptReturn();

        }
    }


}
