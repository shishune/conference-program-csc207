package presenter;

import java.util.List;

/**
 * A presenter class. This class is responsible for anything related to displaying events to an organizer.
 * @author multiple
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
        System.out.println("This room is double-booked at this time.");
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

    public void badTime(){System.out.println("Time is not valid. Try again.");}

    public void promptViewContacts(){
        System.out.println("Press 'x' if you want view your contacts according to event or any key to view all contacts together");
    }
    public void promptEvent(){
        System.out.println("Please enter the name of the event you would like view your contacts from of X to go back");
    }
    public void notYourEvent(){
        System.out.println("You are not charge of this event, please try again");
    }

    public void eventExists(){System.out.println("This event name is already taken.");}

    public void noEvent(){System.out.println("This event does not exist, please try again.");}

    public void allYourContacts(List<String> contacts){
        System.out.println(contacts.toString());
    }

    public void allYourContactsEvent(List<String> eventContacts){
        System.out.println(eventContacts.toString());
    }

    public void breakPlease(){System.out.println("*Write '!' if you would like exit the selection at any time. Doing so will not save your current information.*");}

    public void warning(){System.out.println("WARNING: You are about to exit without saving your information. Enter 'x' to continue or any key to go back.");}

    //public void
}
