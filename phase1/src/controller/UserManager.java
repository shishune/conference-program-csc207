package controller;

import entities.Event;
import entities.Room;
import entities.User;
import entities.Message;
import useCase.*;

import java.time.LocalDateTime;
import java.util.*;

public class UserManager {

    public boolean sendMessage(String sender, String receiver, String content){
        UserAccountActions user = new UserAccountActions();
        if (user.findUserFromUsername(sender).getContactsList().contains(receiver)){
            MessageActions newMessage = new MessageActions();
            newMessage.createMessage(sender, receiver, content);
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

//    public String viewMessageOneSender (String fromMe){
//        MessageActions messageActions = new MessageActions();
//        return messageActions.printMessages(fromMe).toString();
//    };

    public String viewMessages (String fromMe, String toMe) {
        MessageActions messageActions = new MessageActions();
        return messageActions.printMessages(fromMe, toMe).toString();
    }

    public boolean signupEvent(String event, String user){
        EventActions e = new EventActions();
        e.events.get(event).addAttendee(user);

        Event e1 = e.events.get(event);
        AttendeeActions a = new AttendeeActions();
        User a1 = a.usersHashMap.get(user);

        if (checkConflictSpots(event) && (checkConflictTime(user, event))){
            e.addAttendee(e1.getId(), a1.getId());
            a1.getEventList().add(event);
            return true;
        }
        return false;
    };

//    public boolean cancelSpotEvent(String event, String user){
//        AttendeeActions a = new AttendeeActions();
//        User a1 = a.usersHashMap.get(user);
//        String userId = a1.getId();
//        if (userId.charAt(0) == 'A') {
//            if () {
//                EventActions e = new EventActions();
//                e.events.get(event).removeAttendee(user);
//
//                Event e1 = e.events.get(event);
//                AttendeeActions a = new AttendeeActions();
//                User a1 = a.usersHashMap.get(user);
//
//                e.removeAttendee(e1.getId(), a1.getId());
//                a1.getEventList().remove(event);
//                return true;
//            }
//        }
//
//    };
    //TODO: organizers are the only ones that can cancel events (unfinished)

    public String viewOwnSchedule(String user){
        AttendeeActions a = new AttendeeActions();
        User a1 = a.usersHashMap.get(user);
        return a1.getEventList().toString();
    };

    public String viewAvailableSchedule(String user){
        AttendeeActions a = new AttendeeActions();
        User a1 = a.usersHashMap.get(user);

        EventActions e = new EventActions();
        Set<String> allEvents = e.events.keySet();
        StringBuilder availableS = new StringBuilder();
        List<String> targetList = new ArrayList<>(allEvents);

        for (String s: targetList){
            if (!(checkConflictTime(user, s) && checkConflictSpots(s))){
                availableS.append(s);
            }
        }
        return availableS.toString();

//        Iterator<HashMap.Entry<String,Event>> it = e.events.entrySet().iterator();
//        while (it.hasNext()){
//            HashMap.Entry<String, Event> p = (HashMap.Entry <String, Event>)it.next();
//            availableS.append(p);
//            it.remove();
//        }
//        return availableS.toString();
        //TODO: this might not work, would rather use a list

//        String availableEvents;
//        for (int i = 0; i < allEvents.size(); i++) {
//
//            Event curr = e.events.get()

    }

    public int spotsAvailable(String event){
        EventActions e = new EventActions();
        String room = e.events.get(event).getRoomID();

        RoomActions r = new RoomActions();
        Room r1 = r.returnHashMap().get(room);

        return r1.getCapacity() - e.events.get(event).getAttendees().size();

    };

    private boolean checkConflictTime(String username, String event){
        //return true if there is a conflict
        EventActions e = new EventActions();
        String timeEvent = e.events.get(event).getDateTime();

        UserAccountActions u = new UserAccountActions();
        User user = u.findUserFromUsername(username);

        for (int i = 0; i < user.getEventList().size(); i++){

            String name = user.getEventList().get(i);

            EventActions eO = new EventActions();

            String time = eO.events.get(name).getDateTime();

            if (time.equals(timeEvent)){
                return true;
            }
        }

        return false;
    };

    private boolean checkConflictSpots(String event){
        return spotsAvailable(event) == 0;

    }

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

