package presenters;
import java.util.List;
/**
 * A presenter class. This class is responsible for anything related to displaying events to the user.
 * @author multiple
 * @version 1
 * */
public class EventPresenter {
    private String enterNameOfEvent = "Please enter the name of the event you would like to ";
    private String eventSuccess = "This event has been successfully ";
    private String genericRoomSuccess = "This room has been successfully ";
    private String enterNameofRoom = "Please enter the name of the room you would like to ";

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
        System.out.println(enterNameOfEvent+ "cancel.");
    }


    /**
     * Prompt for user to select an event to add or save
     * */
    public void promptSelectEvent(){
        System.out.println(enterNameOfEvent+ "add or save.");
    }

    /**
     * Prompt for user to add or save an event
     * */
    public void promptAddOrSaveEvent(){
        System.out.println("Enter 'A' to add the event or enter 'S' to save the event");
    }

    /**
     * Prompt for user to add room
     * */
    public void promptAddRoom(){
        System.out.println(enterNameofRoom+ "add.");
    }


    /**
     * Message informing that user cancelled event
     * */
    public void successCancelEnrol(){
        System.out.println(eventSuccess+ "cancelled.");
    }


    /**
     * Message informing that user added event
     * */
    public void successAddEvent(){
        System.out.println(eventSuccess+ "added.");
    }

    /**
     * Message informing that user saved event
     * */
    public void successSaveEvent() {
        System.out.println(eventSuccess+ "saved.");
    }

    /**
     * Message informing that the event is already saved
     * */
    public void failedSaveEvent() {
        System.out.println("The event is already saved.");
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
     * Event not created
     */
    public void notCreated(){
        System.out.println("Your event was not created.");
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

    /**
     * A prompt asking the user for their VIP status
     * */
    public void VIPStatusPrompt(){System.out.println("Please input 'Y' if the attendee is a VIP, otherwise press any other key");}



}
