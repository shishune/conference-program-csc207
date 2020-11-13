package presenter;
import entities.Event;
import java.util.List;
/* TODO:
 * warning that the event is full, user cannot attend (???)
 * “Attendees should not be able to sign up for an event that is already full.”
 */
/**
 * A presenter class. This class is responsible for anything related to displaying events to the user.
 * @author Cynthia
 * @version 1
 * */
public class EventPresenter {
    private String generic = "Please enter the name of the event you would like to ";
    private String generic2 = "This event has been successfully ";
    private String generic3 = "This room has been successfully ";
    private String generic4 = "Please enter the name of the room you would like to ";

    /**
     * Dislays events
     * */
    public void displayEvents(List<Event> eventsList){
        int count = 1;
        for (Event event: eventsList){
            System.out.println(count);
            count ++;
            System.out.println("Event title: "+event.getTitle());
            System.out.println("Event time: "+event.getDateTime());
            System.out.println("Event room: "+event.getRoomID());
            System.out.println("Event speaker: "+event.getSpeaker());
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
     * Prompt for user to remove room
     * */
    public void promptRemoveRoom(){
        System.out.println(generic4+ "remove.");
    }

    /**
     * Prompt for user to reschedule event
     * */
    public void promptRescheduleEvent(){
        System.out.println(generic+ "reschedule.");
    }

    /**
     * Message informing that user cancelled event
     * */
    public void successCancelEvent(){
        System.out.println(generic2+ "cancelled.");
    }

    /**
     * Message informing that user added event
     * */
    public void successAddEvent(){
        System.out.println(generic2+ "added.");
    }

    /**
     * Message informing that user rescheduled event
     * */
    public void successRescheduleEvent(){
        System.out.println(generic2+ "rescheduled.");
    }

    /**
     * Message informing that user added room
     * */
    public void successAddRoom(){
        System.out.println(generic3+ "added.");
    }
    /**
     * Message informing that user removed room
     * */
    public void successRemoveRoom(){
        System.out.println(generic4+ "removed.");
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

}
