package presenters;

import java.util.List;

/**
 * A presenter class. This class is responsible for anything related to displaying events to an organizer.
 * @author multiple
 * @version 1
 * */

public class OrganizerEventPresenter extends EventPresenter {
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
     * Message informing that user failed to rescheduled event
     * */
    public void failedRescheduleEvent(){
        System.out.println("Failed to reschedule event. Check for conflicts with either the speaker or room");
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
        System.out.println("This speaker is booked at this time.");
    }

    /**
     * Message informing that organizer double-booked room
     * */
    public void failedDoubleBookRoom(){
        System.out.println("This room is booked at this time.");
    }

    /***
     * Message informing organizer room capacity is too low
     * @param roomCap capacity of room
     */
    public void roomCapacityLow(int roomCap){
        System.out.println("This room has the capacity of " + roomCap + " and so the event cannot be held there.");
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
    public void promptRescheduleMethod(){
        System.out.println("Enter the name of the event to be rescheduled.");
    }

    /**
     * Asks for the title fo the event to be added
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
        System.out.println("Enter speaker username.");
    }

    /**
     * Asks for room
     */
    public void promptRoom(){
        System.out.println("Enter room name");
    }

    /**
     * Shows that the time is not valid, prompts the user to try again
     */
    public void badTime(){System.out.println("Time is not valid. Try again.");}

    /**
     * Prompts user to view contacts
     */
    public void promptViewContacts(){
        System.out.println("Press 'x' if you want view your contacts according to event or any key to view all contacts together");
    }

    /**
     * Prompts user to view events
     */
    public void promptEvent(){
        System.out.println("Please enter the name of the event you would like view your contacts from of X to go back");
    }

    /**
     * Messages user that they are not in charge of the event
     */
    public void notYourEvent(){
        System.out.println("You are not charge of this event, please try again");
    }

    /**
     * Tells user that the event name is unavailable because it has already been taken
     */
    public void eventExists(){System.out.println("This event name is already taken.");}

    /**
     * Messages user that the event does not exist
     */
    public void noEvent(){System.out.println("This event does not exist, please try again.");}

    /**
     * Contacts of the user
     */
    public void allYourContacts(List<String> contacts){
        System.out.println(contacts.toString());
    }

    /**
     * Events of the contacts of the user
     */
    public void allYourContactsEvent(List<String> eventContacts){
        System.out.println(eventContacts.toString());
    }

    /**
     * Prompts user exit the selection
     */
    public void breakPlease(){System.out.println("*Write '!' if you would like exit the selection at any time. Doing so will not save your current information.*");}

    /**
     * Warns the user that they are existing without saving their information
     */
    public void warning(){System.out.println("WARNING: You are about to exit without saving your information. Enter 'x' to leave or any key to go back.");}

}
