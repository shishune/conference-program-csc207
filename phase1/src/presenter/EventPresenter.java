package presenter;
import entities.Event;
import java.util.List;
/* TODO:
 * display a schedule of events in a given list (convert each event object to the corresponding time and event name) - can be used by any user
 * warning that organizer double-booked a speaker (???)
 * warning that organizer double-booked a room (???)
 * “It should be possible for an Organizer to schedule each speaker to give a talk at specific times in specific rooms, without double-booking a speaker (scheduling them to speak two places at the same time) or double-booking a room (scheduling two people to speak in the same room at the same time). This can be done automatically by your system, by giving the user enough information to avoid such conflicts, or restricting the choices an Organizer can make.”
 * warning that the event is full, user cannot attend (???)
 * “Attendees should not be able to sign up for an event that is already full.”
 */
public class EventPresenter {
    private String generic = "Please enter the name of the event you would like to ";
    private String generic2 = "This event has been successfully ";
    private String generic3 = "This room has been successfully ";
    private String generic4 = "Please enter the name of the room you would like to ";

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
    public void promptCancelEvent(){
        System.out.println(generic+ "cancel.");
    }
    public void promptAddEvent(){
        System.out.println(generic+ "add.");
    }
    public void promptAddRoom(){
        System.out.println(generic4+ "add.");
    }
    public void promptRemoveRoom(){
        System.out.println(generic4+ "remove.");
    }
    public void promptRescheduleEvent(){
        System.out.println(generic+ "reschedule.");
    }
    public void successCancelEvent(){
        System.out.println(generic2+ "cancelled.");
    }
    public void successAddEvent(){
        System.out.println(generic2+ "added.");
    }
    public void successRescheduleEvent(){
        System.out.println(generic2+ "rescheduled.");
    }
    public void successAddRoom(){
        System.out.println(generic3+ "added.");
    }
    public void successRemoveRoom(){
        System.out.println(generic4+ "removed.");
    }
}
