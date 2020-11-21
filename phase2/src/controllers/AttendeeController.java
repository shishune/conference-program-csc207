package controllers;

// import com.sun.org.apache.xpath.internal.operations.Or;
import useCases.RoomActions;

public class AttendeeController extends UserController {
    private useCases.MessageActions message;
    private useCases.EventActions e;
    private useCases.AttendeeActions attendee;

    /**
     *
     * @param events
     * @param rooms
     * @param message
     * @param attendee
     * @param organizer
     * @param speaker
     */
    public AttendeeController(useCases.EventActions events, RoomActions rooms, useCases.MessageActions message,
                              useCases.AttendeeActions attendee, useCases.OrganizerActions organizer, useCases.SpeakerActions speaker ) {
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