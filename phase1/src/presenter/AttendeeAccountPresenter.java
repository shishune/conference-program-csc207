package presenter;

/**
 * A presenter class. This class is responsible for anything related to logging in,
 * logging out, and navigating through the account. It also creates a menu specific to an attendee.
 * @author Cynthia
 * @version 1
 * */
public class AttendeeAccountPresenter extends AccountPresenter{
    String basicMenu = ""
            + "\n[1] Log out"
            + "\n[2] Send a message"
            + "\n[3] View all messages"
            + "\n[4] Add a contact"
            + "\n[5] View all contacts";
    /**
     * Prints the main menu
     * */
    public void printMainMenu(){
        String display = basicMenu
                + "\n[6] Sign up for an event"
                + "\n[7] Cancel enrollment for an event"
                + "\n[8] View all events"
                + "\n[9] View your schedule of events"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }
}
