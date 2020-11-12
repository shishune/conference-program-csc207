package presenter;

/**
 * A presenter class. This class is responsible for anything related to logging in,
 * logging out, and navigating through the account. It also creates a menu specific to an attendee.
 * @author Cynthia
 * @version 1
 * */
public class AttendeeAccountPresenter extends AccountPresenter{
    /**
     * Prints the main menu
     * */
    public void printMainMenu(){
        String display = basicMenu
                + "[6] Sign up for an event"
                + "[7] Cancel enrollment for an event"
                + "[8] View your schedule of events"
                + "[9] View all events"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
