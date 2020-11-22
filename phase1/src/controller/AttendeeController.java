package controller;

// import com.sun.org.apache.xpath.internal.operations.Or;
import controller.UserController;
import useCase.RoomActions;

public class AttendeeController extends UserController {
    private useCase.MessageActions message;
    private useCase.EventActions e;
    private useCase.AttendeeActions attendee;

    /**
     *
     * @param events
     * @param rooms
     * @param message
     * @param attendee
     * @param organizer
     * @param speaker
     */
    public AttendeeController(useCase.EventActions events, RoomActions rooms, useCase.MessageActions message,
                              useCase.AttendeeActions attendee, useCase.OrganizerActions organizer, useCase.SpeakerActions speaker ) {
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