package presenter;

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
}
