package parameterObjects;
import useCases.ConferenceActions;
import useCases.EventActions;
import useCases.MessageActions;
import useCases.RoomActions;

/**
 * Parameter object for all use case classes involved in the system of events. This includes
 * messages, conferences, rooms, and the events themselves.
 */
public class EventSystemActions {

    private ConferenceActions conferenceActions;
    private EventActions eventActions;
    private MessageActions messageActions;
    private RoomActions roomActions;

    public EventSystemActions(ConferenceActions conferenceActions, EventActions eventActions, MessageActions messageActions, RoomActions roomActions){
        this.conferenceActions = conferenceActions;
        this.eventActions = eventActions;
        this.messageActions = messageActions;
        this.roomActions = roomActions;
    }

    public ConferenceActions getConferenceActions() {
        return conferenceActions;
    }

    public EventActions getEventActions() {
        return eventActions;
    }

    public MessageActions getMessageActions() {
        return messageActions;
    }

    public RoomActions getRoomActions() {
        return roomActions;
    }

}
