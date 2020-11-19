package presenter;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * A presenter class. This class is responsible for anything related to displaying messages to the user.
 * Creates menu specific to organizer for the method of messaging.
 * @author Cynthia
 * @version 1
 * */
public class OrganizerMessagePresenter extends MessagePresenter{

    /**
     * prompt to enter event to send message to
     * */
    public void promptEvent(){
        System.out.println("Please enter the name of an event you would like to send a message to.");
    }

    /**
     * print menu for messaging options specific to organizer
     * */
    public void printMenu(){
        String display = ""
                + "\n[1] Send a message to all speakers"
                + "\n[2] Send a message to all attendees"
                + "\n[3] Send a message to a single user"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }

    /**
     * print message for error
     * */
    public void eventNotCreated(){
        String display = "That event does not exist. Try again or create a new event first.";
        System.out.println(display);
    }

    public void speakerUsernamePrompt(){
        String display = "What is the username of the speaker you would like to create?";
        System.out.println(display);
    }

    public void speakerPasswordPrompt(){
        String display = "What is the password of the speaker you would like to create?";
        System.out.println(display);
    }

    public void speakerNotCreated(){
        String display = "This speaker does not exist. Try again or create a new speaker first.";
        System.out.println(display);
    }

    public void speakerCreated(){
        String display = "You have successfully created the speaker.";
        System.out.println(display);
    }

    public void badRoom(){
        System.out.println("That room does not exist. Add a new room by writing 'ADD' or rewrite the name of the room after pressing any key.");
    }
    public void newRoom(){
        System.out.println("Enter the name of the room to be added");
    }
    public void addedRoom(){
        System.out.println("You have successfully created a new room.");
    }
}
