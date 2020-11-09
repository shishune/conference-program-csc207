package controller;

import entities.Event;
import entities.User;
import entities.Message;
import useCase.MessageActions;
import useCase.UserAccountActions;
import useCase.EventActions;

public class UserManager {

    public boolean sendMessage(String sender, String receiver, String content){

        if (sender.getContactsList().contains(receiver.getUsername())){
            MessageActions newMessage = new MessageActions();
            //newMessage.createMessage(sender, receiver, content);
            return true;
        }
        return false;
    };

    public boolean addContact(User addMe, User toMe){
        UserAccountActions userAccount = new UserAccountActions();
        return userAccount.addUserContactList(toMe, addMe);
    };

    public boolean deleteContact(User removeMe, User toMe){
        UserAccountActions userAccount = new UserAccountActions();
        return userAccount.removeUserContactList(toMe, removeMe);
    };

    public String viewMessage(Message fromMe, Message toMe){
        return null;
        //TODO
    };

    public boolean signupEvent(Event event, User user){
        EventActions newEvent = new EventActions();


        boolean added = user.getEventList().add(event.getId());
        boolean result = newEvent.addAttendee(event.getId(), user.getId());
    };

    public boolean cancelSpotEvent(){

    };

    public String viewOwnSchedule(){

    };

    public String viewAvailableSchedule(){

    };

    public int spotsAvailable(){

    };

    public boolean checkConflictTime(String username, Event event){

    };

    public boolean checkConflictSpots(){


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
