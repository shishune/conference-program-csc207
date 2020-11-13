package presenter;

/**
 * A presenter class. This class is responsible for anything related to displaying messages to the user.
 * Creates menu specific to speaker for the method of messaging.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerMessagePresenter extends MessagePresenter{
    /**
     * Prompt to enter a list of events
     * */
    public void promptListEvents(){
        System.out.println("Enter an event or multiple events to send a message.");
    }

    /**
     * print menu for messaging options specific to speaker
     * */
    public void printMenu(){
        String display = ""
                + "\n[1] Send a message to all attendees of an event or multiple events"
                + "\n[2] Send a message to a single attendee"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }
}
