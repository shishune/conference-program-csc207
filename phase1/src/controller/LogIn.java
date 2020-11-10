package controller;

import useCase.EventActions;
import useCase.MessageActions;
import useCase.RoomActions;
import useCase.UserAccountActions;

public class LogIn {
    // calls LoadUp
    // calls next thingy; UserDashboard for example
    // tada! done with LogIn stuff
    // initialize all the usecase classes here
    public LogIn(){
        MessageActions messageActions = new MessageActions();
        EventActions eventActions = new EventActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        RoomActions roomActions = new RoomActions();


    }
}
