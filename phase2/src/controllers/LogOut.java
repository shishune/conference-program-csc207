package controllers;

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
    useCases.ConferenceActions conferenceActions;

    /**
     * Instantiates a new logout object
     * @param store the gateway responsible for storing information regarding rooms, events, messages, and
     *              organizers of the conference
     * @param eventSystemActions the use cases for handling the system of events
     * @param accountActions the use cases for handling the accounts of users
     * @param logoutActions the use case handling logout
     */
    public LogOut(Store store, parameterObjects.EventSystemActions eventSystemActions,
                  parameterObjects.AccountActions accountActions, useCases.LogoutActions logoutActions){
        this.store = store;
        this.messageActions = eventSystemActions.getMessageActions();
        this.organizerActions = accountActions.getOrganizerActions();
        this.attendeeActions = accountActions.getAttendeeActions();
        this.roomActions = eventSystemActions.getRoomActions();
        this.speakerActions = accountActions.getSpeakerActions();
        this.eventActions = eventSystemActions.getEventActions();
        this.logoutActions = logoutActions;
        this.conferenceActions = eventSystemActions.getConferenceActions();

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
        store.storeConferences(conferenceActions);
        store.storeEntities(attendeeActions.getAttendeeIds(), organizerActions.getOrganizerIds(),
                messageActions.getMessengerIds(), roomActions.getRoomsIds(), eventActions.getEventIds(),
                speakerActions.getSpeakerIds(), conferenceActions.getConferenceIds());


        logoutActions.logout(username, type, attendeeActions, organizerActions, speakerActions);


    }

    /**
     * Exits the application
     */
    public void exit(){
        System.exit(1);
    }
}
