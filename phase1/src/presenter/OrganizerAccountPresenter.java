package presenter;

/**
 * A presenter class. This class is responsible for anything related to logging in,
 * logging out, and navigating through the account. It also creates a menu specific to an organizer.
 * @author Cynthia
 * @version 1
 * */
public class OrganizerAccountPresenter extends AccountPresenter{
    /**
     * Prints the main menu
     * */
    public void printMainMenu(){
        String display = ""
                + "\n[6] Add an event"
                + "\n[7] Remove/reschedule an event"
                + "\n[8] View all events"
                + "\n[9] Add room"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }
}
