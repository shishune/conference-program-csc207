package controller;

// import com.sun.org.apache.xpath.internal.operations.Or;
import useCase.*;

public class AttendeeController extends UserController{
    private MessageActions message;
    private EventActions e;
    private AttendeeActions attendee;

    /**
     *
     * @param events
     * @param rooms
     * @param message
     * @param attendee
     * @param organizer
     * @param speaker
     */
    public AttendeeController(EventActions events, RoomActions rooms, MessageActions message,
                              AttendeeActions attendee, OrganizerActions organizer, SpeakerActions speaker ) {
        super(events, rooms, message, 'a', attendee, organizer, speaker);
        this.attendee = attendee;
        this.e = events;

    }

    @Override
    public boolean leaveEvent(String eventName, String userId){
        String eventID = e.getEventFromName(eventName).getId();
        return this.attendee.removeEventFromUser(eventID, userId);
    }

}