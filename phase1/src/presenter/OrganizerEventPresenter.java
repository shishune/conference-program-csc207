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
        System.out.println(generic2+ "cancelled.");
    }
    /**
     * Message informing that user succeeded in rescheduling event
     * */
    public void successRescheduleEvent(){
        System.out.println(generic2+ "rescheduled.");
    }
    /**
     * Message informing that user failed to reschedulled event
     * */
    public void failedRescheduleEvent(){
        System.out.println("Failed to reschedule event. Check for conflicts.");
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
     * Two options: reschedule or cancel event?
     */
    public void promptCancelMethod(){
        System.out.println("If you are cancelling an event, enter 'x'. Otherwise enter any key.");
    }
    /**
     * Asks for title of event to add
     */
    public void promptTitle(){
        System.out.println("Enter the title of the event you want to add.");
    }
    /**
     * Asks for date of event to add
     */
    public void promptDate(){
        System.out.println("Enter the date in DD/MM/YY format followed");
    }
    /**
     * Asks for time of event to add
     */
    public void promptTime(){
        System.out.println("Enter the hour of day as a single integer using the 24-hour clock.");
    }
    /**
     * Asks for speaker
     */
    public void promptSpeaker(){
        System.out.println("Enter speaker username or enter NEW if you want to make a new speaker");
    }
    /**
     * Asks for room
     */
    public void promptRoom(){

    }
}
