package presenters;
import java.util.List;
/**
 * A presenter class. This class is responsible for anything related to displaying events to the user.
 * @author multiple
 * @version 1
 * */
public class EventPresenter {
    private String generic = "Please enter the name of the event you would like to ";
    private String generic2 = "This event has been successfully ";
    private String generic3 = "This room has been successfully ";
    private String generic4 = "Please enter the name of the room you would like to ";

    /**
     * Displays events
     * */
    public void displayEvents(List<List<String>> eventsList){
        int count = 1;
        for (List<String> info: eventsList){
            System.out.println(count);
            count ++;

            System.out.println("Event title: "+info.get(0));
            System.out.println("Event time: "+info.get(1));
            System.out.println("Event room: "+info.get(2));
            System.out.println("Event speaker: "+info.get(3));

            System.out.println("\n");
        }
    }
    /**
     * Prompt for user to cancel event/remove event
     * */
    public void promptCancelEvent(){
        System.out.println(generic+ "cancel.");
    }


    /**
     * Prompt for user to add event/attend event
     * */
    public void promptAddEvent(){
        System.out.println(generic+ "add.");
    }


    /**
     * Prompt for user to add room
     * */
    public void promptAddRoom(){
        System.out.println(generic4+ "add.");
    }


    /**
     * Message informing that user cancelled event
     * */
    public void successCancelEnrol(){
        System.out.println(generic2+ "cancelled.");
    }


    /**
     * Message informing that user added event
     * */
    public void successAddEvent(){
        System.out.println(generic2+ "added.");
    }


    /**
     * Message informing that room is full; user cannot attend
     */
    public void failedRoomFull(){
        System.out.println("Sorry, this room is full.");
    }


    /**
     * Message informing of time conflict
     */
    public void failedAttendeeTimeConflict(){
        System.out.println("There is a time conflict with another event you are attending.");
    }


    /**
     * Message informing that event does not exist
     */
    public void failedNoSuchEvent(){
        System.out.println("This event does not exist.");
    }


    /**
     * General failure
     */
    public void failed(){
        System.out.println("Sorry, there was a problem with your request.");
    }


    /**
     * Failed event cancel
     */
    public void failedCancelEvent(){
        System.out.println("We are not able to cancel this event. Check if you entered the correct name.");
    }


    /**
     * Displays events of the inputted user
     */
    public void viewEvents(List<List<String>> user){
        int count = 1;

        for (List<String> info: user){
            System.out.println(count);
            count ++;

            System.out.println("Event title: "+info.get(0));
            System.out.println("Event time: "+info.get(1));
            System.out.println("Event room: "+info.get(2));
            System.out.println("Attendees: "+info.get(3));

            System.out.println("\n");
        }
    }

}
