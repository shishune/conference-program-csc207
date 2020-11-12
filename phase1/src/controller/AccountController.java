package controller;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
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
        LogIn logIn = new LogIn();
        LogOut logOut = new LogOut();
        LoadUpIGateway g = new LoadUp();

        AccountPresenter accountMessage = new AccountPresenter();

        MessageActions messageActions = new MessageActions(g);
        EventActions eventActions = new EventActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions();
        SpeakerActions speakerActions = new SpeakerActions();
        OrganizerActions organizerActions = new OrganizerActions(g);
        AttendeeActions attendeeActions = new AttendeeActions(g);

        boolean run = true;
        //this loop serves to allow user to return to menu repeatedly
        //run is set to false once the user chooses to exit
        while (run) {
            //login procedure.
            Scanner scan = new Scanner(System.in);  // Create a Scanner object
            accountMessage.promptUsername();
            String username = scan.nextLine();  // Read user input
            accountMessage.promptPassword();
            String password = scan.nextLine();  // Read user input
            //String id = logIn.loggingIn(username, password); // evaluate username/password
            User user = logIn.logIn(username, password, userAccountActions);

            //TODO idk if it is clean architecture to have long if statements like this but at least for now it outlines major tasks
            if (user.getIsOrganizer()){ // indicates organizer
                accountMessage = (OrganizerAccountPresenter) accountMessage; //TODO idk if this works, should test
            }
            else if (user.getId().charAt(0)=='A'){ //indicates attendee
                accountMessage = (AttendeeAccountPresenter) accountMessage;
            }
            else if (user.getId().charAt(0)=='S'){ //indicates speaker
                accountMessage = (SpeakerAccountPresenter) accountMessage;
            }
            else{
                int c; //delete
                //TODO ?
            }

            //TODO idk if it is clean architecture to have long if statements like these but at least for now it outlines major tasks
            //Menu
            accountMessage.printMainMenu();
            String menuOption = scan.nextLine();
            if (menuOption.equals("1")){
                //logout procedure. will loop back to login procedure if user does not exit
                logOut.loggingOut();
                accountMessage.successLogout();
                String choice = scan.nextLine();
                if (choice.equals('x')|| choice.equals('X')){
                    run = false;
                }
            }
            else if(menuOption.equals("2")){
                //do something
                accountMessage.promptReturn();
            }
            else if(menuOption.equals("2")){
                //do something
                accountMessage.promptReturn();
            }
            else if(menuOption.equals("3")){
                //do something
                accountMessage.promptReturn();
            }
            else if(menuOption.equals("4")){
                //do something
                accountMessage.promptReturn();
            }
            else if(menuOption.equals("5")){
                //do something
                accountMessage.promptReturn();
            }
            else if(menuOption.equals("6")){
                //do something
                accountMessage.promptReturn();
            }
            else if(menuOption.equals("7")){
                //do something
                accountMessage.promptReturn();
            }
            else{
                int c; //delete
                //TODO ?
            }


        }

        //outside loop. will not return to menu
        logOut.exit();
    }


}
