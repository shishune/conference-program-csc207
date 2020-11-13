package presenter;
public class SpeakerMessagePresenter extends MessagePresenter{

    public void promptListEvents(){
        System.out.println("Enter an event or multiple events to send a message.");
    }
    public void printMenu(){
        String display = ""
                + "\n[1] Send a message to all attendees of an event or multiple events"
                + "\n[2] Send a message to a single attendee"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }
}
