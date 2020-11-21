package controller;

import gateways.Store;
import useCases.RoomActions;

/**
 * Allows user to exit the system with their information stored
 */
public class LogOut {
    Store store;
    useCases.MessageActions messageActions;
    useCases.OrganizerActions organizerActions;
    useCases.AttendeeActions attendeeActions;
    useCases.RoomActions roomActions;
    useCases.SpeakerActions speakerActions;
    useCases.EventActions eventActions;
    useCases.LogoutActions logoutActions;

    /**
     * Instantiates a new logout object
     * @param store the gateway responsible for storing information regarding rooms, events, messages, and
     *              organizers of the conference
     * @param messageActions the use case responsible for messages
     * @param organizerActions the use case responsible for organizers
     * @param attendeeActions the use case responsible for attendees
     * @param roomActions the use case responsible for rooms
     * @param speakerActions the use case responsible for speakers
     * @param eventActions the use case responsible for events
     */
    public LogOut(Store store, useCases.MessageActions messageActions, useCases.OrganizerActions organizerActions,
                  useCases.AttendeeActions attendeeActions, RoomActions roomActions, useCases.SpeakerActions speakerActions,
                  useCases.EventActions eventActions, useCases.LogoutActions logoutActions){
        this.store = store;
        this.messageActions = messageActions;
        this.organizerActions = organizerActions;
        this.attendeeActions = attendeeActions;
        this.roomActions = roomActions;
        this.speakerActions = speakerActions;
        this.eventActions = eventActions;
        this.logoutActions = logoutActions;

    }

    /**
     * Stores information for logging out
     * @param username A string the user inputs as their username
     * @param type A string that represents the type of user
     */
    public void loggingOut(String username, String type) {

        // login procedure...
        store.storeMessages(messageActions);
        store.storeRooms(roomActions);
        store.storeEvents(eventActions);
        store.storeOrganizers(organizerActions);
        store.storeAttendees(attendeeActions);
        store.storeSpeakers(speakerActions);
        store.storeEntities(attendeeActions.getAttendeeIds(), organizerActions.getOrganizerIds(),
                messageActions.getMessengerIds(), roomActions.getRoomsIds(), eventActions.getEventIds(),
                speakerActions.getSpeakerIds());

        logoutActions.logout(username, type, attendeeActions, organizerActions, speakerActions);
    }

    /**
     * Exits the application
     */
    public void exit(){
        System.exit(1);
    }
}
