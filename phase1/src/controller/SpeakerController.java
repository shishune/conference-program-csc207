package controller;
import entities.Event;
import entities.Message;
import entities.Speaker;
import presenter.SpeakerMessagePresenter;
import useCase.*;
import java.util.HashMap;
import entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/***
 * A speaker controller. Includes all the abilities only speakers can complete.
 */

public class SpeakerController extends UserController {
    private String SpeakerID;
    public MessageActions messageActions; // = super.getMessages();
    public EventActions eventActions; // = super.getEvents();
    public UserAccountActions userAccountActions; // = super.getUserAccountActions();
    public RoomActions roomActions; // = super.getRooms();
    public SpeakerActions speakerActions; // = super.getSpeakers();
    public OrganizerActions organizerActions; // = super.getOrganizers();
    public AttendeeActions attendeeActions; // = super.getAttendees();
    private SpeakerMessagePresenter displayMessage;

    public SpeakerController(String SpeakerID, MessageActions messageActions, EventActions eventActions, RoomActions roomActions,
                             AttendeeActions attendeeActions, OrganizerActions organizerActions, SpeakerActions speakerActions) {
        super(eventActions, roomActions, messageActions, 's', attendeeActions, organizerActions, speakerActions);

        this.SpeakerID = SpeakerID;
        this.messageActions = messageActions;
        this.eventActions = eventActions;
        this.roomActions = roomActions;
        this.attendeeActions = attendeeActions;
        this.organizerActions = organizerActions;
        this.speakerActions = speakerActions;
        this.displayMessage = new SpeakerMessagePresenter();
    }
    /**
     *
     * Allows Speakers to send a message to those attendees attending their event
     * */
    public boolean sendMessages(String event, String message) {
        if(eventActions != null) {
            HashMap<String, Event> eventHash = eventActions.getEventNames();
            if(eventHash.get(event) != null) {
                String eventID = eventHash.get(event).getId();
                List<String> attendees = eventActions.getEventAttendees(eventID);
                for (String attendeeID : attendees) {
                    if(this.messageActions.createMessage(this.SpeakerID, attendeeID, message) == null){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     *
     * Allows Speakers to view attendees attending their event
     * */

    public List<String> viewAttendees(String eventID) {
        return this.eventActions.getEventAttendees(eventID);
    }

    public List<String> viewEvents(String speakerID){return speakerActions.returnIDHashMap().get(speakerID).getEventList();}

}