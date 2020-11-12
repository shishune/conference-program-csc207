package presenter;

public class OrganizerAccountPresenter extends AccountPresenter{
    public void printMainMenu(){
        String display = ""
                + "[1] Send a message"
                + "[2] View all messages"
                + "[3] Add an event"
                + "[4] Remove an event"
                + "[5] View all events"
                + "[6] Add room"
                + "[7] Remove room"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
