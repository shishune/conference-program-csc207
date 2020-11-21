package presenter;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * A presenter class. This class is responsible for anything related to displaying messages to the user.
 * Creates menu specific to organizer for the method of messaging.
 * @author multiple
 * @version 1
 * */
public class OrganizerMessagePresenter extends MessagePresenter{

    /**
     * Prompt to enter event to send message to
     * */
    public void promptEvent(){
        System.out.println("Please enter the name of an event you would like to send a message to."
                + "\nYou can see a list of events by selecting option 8 in the main menu");
    }

    /**
     * Print menu for messaging options specific to organizer
     * */
    public void printMenu(){
        String display = ""
                + "\n[1] Send a message to all speakers"
                + "\n[2] Send a message to all attendees of an event."
                + "\n[3] Send a message to a single user"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }

    /**
     * Print message for error
     * */
    public void eventNotCreated(){
        String display = "That event does not exist. Try again or create a new event first.";
        System.out.println(display);
    }

    /**
     * Prompt to enter the username of the speaker to create
     */
    public void speakerUsernamePrompt(){
        String display = "What is the username of the speaker you would like to create?";
        System.out.println(display);
    }

    /**
     * Prompt to enter the password of the speaker to create
     */
    public void speakerPasswordPrompt(){
        String display = "What is the password of the speaker you would like to create?";
        System.out.println(display);
    }

    /**
     * Message stating that the user does not exist, prompts user to try again or create a new speaker
     */
    public void speakerNotCreated(){
        String display = "This speaker does not exist. Try again or create a new speaker first.";
        System.out.println(display);
    }

    /**
     * Message stating that the speaker has been successfully created
     */
    public void speakerCreated(){
        String display = "You have successfully created the speaker.";
        System.out.println(display);
    }

    /**
     * Message stating that the room does not exist and that the user can create a new room or rewrite the name of the room
     */
    public void badRoom(){
        System.out.println("That room does not exist. Add a new room by writing 'ADD' or rewrite the name of the room after pressing any key.");
    }
//    public void newRoom(){
//        System.out.println("Enter the name of the room to be added");
//    }

    /**
     * Message stating that the user successfully created a new room
     */
    public void addedRoom(){
        System.out.println("You have successfully created a new room.");
    }

    /**
     * Message stating that the room already exists
     */
    public void alreadyAddedRoom(){System.out.println("That room already exists.");}

    /**
     * Message stating that the speaker already exists
     */
    public void alreadySpeaker(){System.out.println("This speaker already exists.");}

    /**
     * Message prompting the user to create a new speaker or to press another key if speaker exists
     */
    public void newOrNoSpeaker(){System.out.println("Enter 'NEW' to create new speaker or another other key if speaker exists.");}

    /**
     * Message explaining how to cancel an event
     */
    public void promptCancelMethod() {
        System.out.println("If you are cancelling an event, enter 'x'. Otherwise enter any key.");
    }
}
