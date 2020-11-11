package controller;

import gateway.LoadUp;
import gateway.LoadUpIGateway;
import entities.Organizer;
import useCase.*;

import java.util.ArrayList;

public class LogIn {
    public MessageActions messageActions;
    public EventActions eventActions;
    public UserAccountActions userAccountActions;
    public RoomActions roomActions;
    public SpeakerActions speakerActions;
    public OrganizerActions organizerActions;

    // calls LoadUp
    // calls next thingy; UserDashboard for example
    // tada! done with LogIn stuff
    // initialize all the usecase classes here
    public LogIn(){
        LoadUpIGateway g = new LoadUp();
        MessageActions messageActions = new MessageActions(g);
        // how to fix controller --> messageAction.loadup(g); if this works??
        EventActions eventActions = new EventActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions();
        SpeakerActions speakerActions = new SpeakerActions();
        OrganizerActions organizerActions = new OrganizerActions();


        // please move this information into the login if statement or however that works. i just need an organizer
        // to be passsed into the organizer controller - eryka
        String organizerID = "O1";
        OrganizerController organizerController = new OrganizerController(organizerID,  messageActions, eventActions,
                userAccountActions, roomActions, speakerActions, organizerActions);

    }
}
