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
        String display = basicMenu
                + "[6] Add an event"
                + "[7] Remove an event"
                + "[8] View all events"
                + "[9] Add room"
                + "[10] Remove room"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
