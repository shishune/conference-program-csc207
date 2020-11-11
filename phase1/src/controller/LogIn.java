package controller;

import entities.Attendee;
import entities.User;
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
    public AttendeeActions attendeeActions;

    // calls LoadUp
    // calls next thingy; UserDashboard for example
    // tada! done with LogIn stuff
    // initialize all the usecase classes here


    public String loggingIn(String username, String password) {

        LoginActions l = new LoginActions();
        UserAccountActions u = new UserAccountActions();

        LoadUpIGateway g = new LoadUp();
        MessageActions messageActions = new MessageActions(g);
        // how to fix controller --> messageAction.loadup(g); if this works??
        EventActions eventActions = new EventActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions();
        SpeakerActions speakerActions = new SpeakerActions();
        OrganizerActions organizerActions = new OrganizerActions();
        AttendeeActions attendeeActions = new AttendeeActions();

        if (l.isLogin(username, password)) {
            return u.usersHashMap.get(username).getId();
            }

            return "LogIn Failed";
        }
    }
