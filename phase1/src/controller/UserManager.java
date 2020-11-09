package controller;

import entities.Event;
import entities.User;
import entities.Message;
import useCase.MessageActions;
import useCase.UserAccountActions;
import useCase.EventActions;

public class UserManager {

    public boolean sendMessage(String sender, String receiver, String content){
        UserAccountActions user = new UserAccountActions();
        if (user.findUserFromUsername(sender).getContactsList().contains(receiver)){
            MessageActions newMessage = new MessageActions();
            //newMessage.createMessage(sender, receiver, content);
            return true;
        }
        return false;
    };

    public boolean addContact(String addMe, String toMe){
        UserAccountActions userAccount = new UserAccountActions();
        return userAccount.addUserContactList(toMe, addMe);
    };

    public boolean deleteContact(String removeMe, String toMe){
        UserAccountActions userAccount = new UserAccountActions();
        return userAccount.removeUserContactList(toMe, removeMe);
    };

    public String viewMessage(Message fromMe, Message toMe){
        return null;
        //TODO
    };

    public boolean signupEvent(String event, String user){
        EventActions newEvent = new EventActions();

        //Event event1 = newEvent.getEvent(event)
        //if checkConfictTime and checkConfictSpots are both false, then add user

//
//        boolean added = user.getEventList().add(event.getId());
//        boolean result = newEvent.addAttendee(event.getId(), user.getId());
    };

    public boolean cancelSpotEvent(){

    };

    public String viewOwnSchedule(){

    };

    public String viewAvailableSchedule(){

    };

    public int spotsAvailable(){

    };

    public boolean checkConflictTime(String username, String event){
        //return true if there is a conflict
        //Event e = new EventAction
        //e.getDateTime

    };

    public boolean checkConflictSpots(String username, String event){


    };

}

/*      Send and receive messages
        Add contacts
        Delete/Block Contact
        View Message
        Sign-up for events
        Cancel Spot in Event
        View their own schedule (s)
        View available schedule(s)
        Number of spots left
        Check conflict with timing and spots*/
