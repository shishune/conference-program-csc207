package presenter;

/**
 * A presenter class. This class is responsible for anything related to displaying events to an organizer.
 * @author Cynthia
 * @version 1
 * */
public class OrganizerEventPresenter extends EventPresenter{
    private String generic2 = "This event has been successfully ";
    private String generic3 = "This room has been successfully ";
    /**
     * Message informing that user cancelled event
     * */
    public void successCancelEvent(){
        System.out.println(generic2+ "cancelled/rescheduled.");
    }

    /**
     * Message informing that user added room
     * */
    public void successAddRoom(){
        System.out.println(generic3+ "added.");
    }

    /**
     * Message informing that organizer double-booked speaker
     * */
    public void failedDoubleBookSpeaker(){
        System.out.println("This speaker is double-booked at this time. Please enter another time.");
    }

    /**
     * Message informing that organizer double-booked room
     * */
    public void failedDoubleBookRoom(){
        System.out.println("This room is double-booked at this time. Please enter another time.");
    }

    /**
     * Asks for title of event to add
     */
    public void promptTitle(){
        System.out.println("Enter the title of the event you want to add.");
    }
    /**
     * Asks for time of event to add
     */
    public void promptTime(){

    }
}
