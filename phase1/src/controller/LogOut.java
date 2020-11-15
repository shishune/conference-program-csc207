package controller;

import entities.Attendee;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import presenter.AccountPresenter;
import useCase.*;
import gateway.Store;

/**
 * Allows user to exit the system with their information stored
 */
public class LogOut {
    Store store;
    MessageActions messageActions;
    OrganizerActions organizerActions;
    AttendeeActions attendeeActions;
    RoomActions roomActions;
    SpeakerActions speakerActions;
    EventActions eventActions;
    LogoutActions logoutActions;
    UserAccountActions userActions;

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
    public LogOut(Store store, UserAccountActions userActions, MessageActions messageActions, OrganizerActions organizerActions,
                  AttendeeActions attendeeActions, RoomActions roomActions, SpeakerActions speakerActions,
                  EventActions eventActions, LogoutActions logoutActions){
        this.store = store;
        this.messageActions = messageActions;
        this.organizerActions = organizerActions;
        this.attendeeActions = attendeeActions;
        this.roomActions = roomActions;
        this.speakerActions = speakerActions;
        this.eventActions = eventActions;
        this.logoutActions = logoutActions;
        this.userActions = userActions;

    }

    /**
     * Stores information for logging out
     */
    public void loggingOut(String username) {

        // Store store = new Store();
        // UserAccountActions u = new UserAccountActions();
        // login procedure...
        store.storeMessages(messageActions);
        store.storeRooms(roomActions);
        store.storeEvents(eventActions);
        store.storeOrganizers(organizerActions);
        store.storeAttendees(attendeeActions);
        // store.storeSpeakers(speakerActions);

        logoutActions.logout(username, userActions);
    }

    /**
     * Exits the application
     */
    public void exit(){
        System.exit(1);
    }
}
