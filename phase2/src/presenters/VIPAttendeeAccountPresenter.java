package presenters;

public class VIPAttendeeAccountPresenter extends AccountPresenter{

    /**
     * Prints VIP main menu
     */
    public void printMainMenu(){
        String basicMenu = ""
                + "\n[1] Log out"
                + "\n[2] Send a message"
                + "\n[3] View all messages"
                + "\n[4] Add a contact"
                + "\n[5] View all contacts";

        String display = basicMenu
                + "\n[6] Sign up for an event / Save an event"
                + "\n[7] Cancel enrollment for an event"
                + "\n[8] View all events"
                + "\n[9] View your schedule of events"
                + "\n[10] View your saved events"
                + "\n[11] View VIP events"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }
}
