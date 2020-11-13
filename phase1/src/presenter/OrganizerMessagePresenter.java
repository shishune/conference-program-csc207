package presenter;
public class OrganizerMessagePresenter extends MessagePresenter{
    public void promptEvent(){
        System.out.println("Please enter the name of an event you would like to send a message to.");
    }
    public void printMenu(){
        String display = ""
                + "\n[1] Send a message to all speakers"
                + "\n[2] Send a message to a single speaker"
                + "\n[3] Send a message to all attendees"
                + "\n[4] Send a message to a single attendee"
                + "\nPlease select a menu item number.";
        System.out.println(display);
    }
}
