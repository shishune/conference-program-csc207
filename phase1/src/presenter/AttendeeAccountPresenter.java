package presenter;

public class AttendeeAccountPresenter extends AccountPresenter{
    public void printMainMenu(){
        String display = ""
                + "[1] Send a message"
                + "[2] View all messages"
                + "[3] Sign up for an event"
                + "[4] Cancel enrollment for an event"
                + "[5] View your schedule of events"
                + "[6] View all events"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
