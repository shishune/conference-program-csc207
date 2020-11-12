package controller;

import entities.Attendee;
import entities.User;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import entities.Organizer;
import useCase.*;
import presenter.AccountPresenter;

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

    //Cynthia: made some edits to interact with presenter classes
    public String loggingIn(String username, String password) {

        LoginActions l = new LoginActions();
        UserAccountActions u = new UserAccountActions();
        AccountPresenter messages = new AccountPresenter();

        LoadUpIGateway g = new LoadUp();
        MessageActions messageActions = new MessageActions(g);
        EventActions eventActions = new EventActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions();
        SpeakerActions speakerActions = new SpeakerActions();
        OrganizerActions organizerActions = new OrganizerActions(g);
        AttendeeActions attendeeActions = new AttendeeActions(g);

        if (l.isLogin(username, password)) {
            return u.usersHashMap.get(username).getId();
        }

        messages.failedLogin();
        return null;

    }

    //alternative. let me know if you disagree
    public User logIn(String username, String password, UserAccountActions u) {
        LoginActions l = new LoginActions();
        if (l.isLogin(username, password)) {
            return u.usersHashMap.get(username);
        }
        return null;
    }
}
