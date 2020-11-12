package controller;

import entities.Attendee;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import presenter.AccountPresenter;
import useCase.*;
import gateway.Store;

public class LogOut {
    Store store;
    MessageActions messageActions;
    OrganizerActions organizerActions;
    AttendeeActions attendeeActions;
    RoomActions roomActions;
    SpeakerActions speakerActions;
    EventActions eventActions;

    public LogOut(Store store, MessageActions messageActions, OrganizerActions organizerActions,
                  AttendeeActions attendeeActions, RoomActions roomActions, SpeakerActions speakerActions,
                  EventActions eventActions){
        this.store = store;
        this.messageActions = messageActions;
        this.organizerActions = organizerActions;
        this.attendeeActions = attendeeActions;
        this.roomActions = roomActions;
        this.speakerActions = speakerActions;
        this.eventActions = eventActions;

    }
    public void loggingOut() {

        // Store store = new Store();
        // UserAccountActions u = new UserAccountActions();
        // login procedure...
        store.storeMessages(messageActions);
        store.storeRooms(roomActions);
        store.storeEvents(eventActions);
        store.storeOrganizaers(organizerActions);
        store.storeAttendees(attendeeActions);
        store.storeSpeakers(speakerActions);
    }

    public void exit(){
        System.exit(1);
    }
}
