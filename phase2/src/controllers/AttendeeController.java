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

    /**
     * Saves an event to list of saved events for an user.
     * @param eventName the name of the event to be save
     * @param userName the username of the user who's saved event list is updated
     * @return true if the event is successfully saved return false if not
     */
    public boolean saveEvent(String eventName, String userName){
        return this.attendee.addEventToSavedEvent(eventName, userName);
    }

    /**
     * Removes an event from list of saved events for an user
     * @param eventName the name of the event to be removed
     * @param userID the id of the user who's saved event list is updated
     * @return true if the event is successfully removed return false if not
     */
    public boolean unSaveEvent(String eventName, String userID){
        String eventID = e.getEventFromName(eventName).getId();
        return this.attendee.removeEventFromSavedEvent(eventID, userID);
    }
}