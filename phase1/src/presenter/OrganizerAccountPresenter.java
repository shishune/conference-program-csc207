package presenter;

public class OrganizerAccountPresenter extends AccountPresenter{
    public void printMainMenu(){
        String display = ""
                + "[1] Send a message"
                + "[2] View all messages"
                + "[3] Add a contact"
                + "[4] View all contacts"
                + "[5] Add an event"
                + "[6] Remove an event"
                + "[7] View all events"
                + "[8] Add room"
                + "[9] Remove room"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
