package controller;

import entities.Event;
import entities.Room;
import entities.User;
import entities.Message;
import useCase.*;

import java.time.LocalDateTime;
import java.util.*;

public class UserController {
    //todo should these be private?
    UserAccountActions user;
    MessageActions message;
    EventActions e;
    AttendeeActions attendee;
    RoomActions room;


    public UserController(UserAccountActions user, EventActions events, RoomActions rooms, MessageActions message, AttendeeActions attendee) {
        this.user = user;
        this.message = message;
        this.e = events;
        this.room = rooms;
        this.attendee = attendee;
    }

    public boolean sendMessage(String sender, String receiver, String content){
        if (user.findUserFromUsername(sender).getContactsList().contains(receiver)){
            message.createMessage(sender, receiver, content);
            return true;
        }
        return false;
    };

    public boolean addContact(String addMe, String toMe){
        return user.addUserContactList(toMe, addMe);
    };

    public boolean deleteContact(String removeMe, String toMe){
        return user.removeUserContactList(toMe, removeMe);
    };

//    public String viewMessageOneSender (String fromMe){
//        MessageActions messageActions = new MessageActions();
//        return messageActions.printMessages(fromMe).toString();
//    };

    //edited to work with presenter, let me know if you disagree
    public ArrayList<ArrayList<String>> viewMessages (String fromMe, String toMe) {
        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        List<Message> messageList = message.printMessages(fromMe, toMe);
        for (Message message:messageList){
            ArrayList<String> info = new ArrayList<String>();
            info.add(user.findUserFromId(message.getSenderId()).getUsername());
            info.add(user.findUserFromId(message.getReceiverId()).getUsername());
            info.add(message.getMessage());
            messages.add(info);
        }
        return messages;
    }

    //need this for presenter
    public ArrayList<String> viewContacts (String userid){
        ArrayList<String> contacts = new ArrayList<String>();
        List<String> usersList = user.findUserFromId(userid).getContactsList();
        for (String id:usersList){
            contacts.add(user.findUsernameFromId(id));
        }
        return contacts;
    }

    //edited so that presenter can print fail messages based on why user cannot attend event
    public List<Boolean> signupEvent(String event, String user){
        List<Boolean> checks = new ArrayList<Boolean>();
        if (!e.eventExists(event)){
            checks.add(false);
            return checks;
        }
        e.getEvent(event).addAttendee(user);

        Event e1 = e.getEvent(event);
        User a1 = attendee.usersHashMap.get(user);


        if (checkConflictSpots(event) && (checkConflictTime(user, event))){
            e.addAttendee(e1.getId(), a1.getId());
            a1.getEventList().add(event);
            checks.add(true);
            return checks;
        }
        checks.add(false);
        if (!checkConflictSpots(event)){
            checks.add(true);
        }
        if (!checkConflictTime(user, event)){
            checks.add(false);
        }
        return checks;
    };

    public String viewOwnSchedule(String user){
        User a1 = attendee.usersHashMap.get(user);
        return a1.getEventList().toString();
    };

    public String viewAvailableSchedule(String user){
        User a1 = attendee.usersHashMap.get(user);

        Set<String> allEvents = e.getEvents().keySet();
        StringBuilder availableS = new StringBuilder();
        List<String> targetList = new ArrayList<>(allEvents);

        for (String s: targetList){
            if (!(checkConflictTime(user, s) && checkConflictSpots(s))){
                availableS.append(s);
            }
        }
        return availableS.toString();
    }

    public int spotsAvailable(String event){
        String rooms = e.getEvent(event).getRoomID();

        Room r1 = room.returnHashMap().get(rooms);

        return r1.getCapacity() - e.getEvent(event).getAttendees().size();

    };

    private boolean checkConflictTime(String username, String event){
        //return true if there is a conflict
        String timeEvent = e.getEvent(event).getDateTime();

        User u = user.findUserFromUsername(username);

        for (int i = 0; i < u.getEventList().size(); i++){

            String name = u.getEventList().get(i);

            String time = e.getEvent(name).getDateTime();

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
//ODO: organizers are the only ones that can cancel events (unfinished)

//------------------------------------------

//        Iterator<HashMap.Entry<String,Event>> it = e.events.entrySet().iterator();
//        while (it.hasNext()){
//            HashMap.Entry<String, Event> p = (HashMap.Entry <String, Event>)it.next();
//            availableS.append(p);
//            it.remove();
//        }
//        return availableS.toString();
//ODO: this might not work, would rather use a list

//        String availableEvents;
//        for (int i = 0; i < allEvents.size(); i++) {
//
//            Event curr = e.events.get()

//------------------------------------------

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

