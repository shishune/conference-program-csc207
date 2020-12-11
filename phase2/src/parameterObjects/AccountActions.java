package parameterObjects;
import useCases.AttendeeActions;
import useCases.SpeakerActions;
import useCases.OrganizerActions;

/**
 * Parameter object for all use cases involving using the account to access the system of events.
 * This includes account actions for attendees, speakers, and organizers.
 */
public class AccountActions {
    private AttendeeActions attendeeActions;
    private SpeakerActions speakerActions;
    private OrganizerActions organizerActions;

    public AccountActions(AttendeeActions attendeeActions, SpeakerActions speakerActions, OrganizerActions organizerActions){
        this.attendeeActions = attendeeActions;
        this.speakerActions = speakerActions;
        this.organizerActions = organizerActions;
    }

    public AttendeeActions getAttendeeActions(){
        return this.attendeeActions;
    }

    public SpeakerActions getSpeakerActions(){
        return this.speakerActions;
    }

    public OrganizerActions getOrganizerActions(){
        return this.organizerActions;
    }

}
