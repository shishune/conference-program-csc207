package controller;

import entities.Event;
import entities.Room;
import entities.User;
import entities.Message;
import useCase.*;

import java.time.LocalDateTime;
import java.util.Date;

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
        EventActions e = new EventActions();
        e.events.get(event).addAttendee(user);

        Event e1 = e.events.get(event);
        AttendeeActions a = new AttendeeActions();
        User a1 = a.usersHashMap.get(user);

        if (checkConflictSpots(user, event) && (checkConflictTime(user, event))){
            e.addAttendee(e1.getId(), a1.getId());
            a1.getEventList().add(event);
            return true;
        }
        return false;
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
        EventActions e = new EventActions();
        String timeEvent = e.events.get(event).getDateTime();

        UserAccountActions u = new UserAccountActions();
        User user = u.findUserFromUsername(username);

        for (int i = 0; i < user.getEventList().size(); i++){

            String name = user.getEventList().get(i);

            EventActions eO = new EventActions();

            String time = eO.events.get(name).getDateTime();

            if (time == timeEvent){
                return true;
            }
        }

        return false;
    };

    public boolean checkConflictSpots(String username, String event){
        EventActions e = new EventActions();
        String room = e.events.get(event).getRoomID();

        RoomActions r = new RoomActions();
        Room r1 = r.returnHashMap().get(room);

        if (r1.getCapacity() - e.events.get(event).getAttendees().size() > 0){
            return true;
        }
        return false;

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
