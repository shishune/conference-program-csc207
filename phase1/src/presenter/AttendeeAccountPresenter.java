package presenter;

public class AttendeeAccountPresenter extends AccountPresenter{
    public void printMainMenu(){
        String display = ""
                + "[1] Send a message"
                + "[2] View all messages"
                + "[3] Add a contact"
                + "[4] View all contacts"
                + "[5] Sign up for an event"
                + "[6] Cancel enrollment for an event"
                + "[7] View your schedule of events"
                + "[8] View all events"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
