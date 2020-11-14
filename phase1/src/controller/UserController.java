package controller;

import entities.Event;
import entities.Room;
import entities.User;
import entities.Message;
import useCase.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A controller class for users. UserContoller is a parent class to OrganizerController, AccountController and
 * SpeakerController.
 * @author Jiessie and Mizna
 * @version 1
 * */

public class UserController {
    //todo should these be private?
    protected UserAccountActions user;
    protected MessageActions message;
    protected EventActions e;
    protected AttendeeActions attendee;
    protected RoomActions room;

    /**
     * Instantiates a new UserController object. Creates an instance of UserAccountActions, MessageActions, EventActions
     * AttendeeActions, RoomActions.
     * */
    public UserController(UserAccountActions user, EventActions events, RoomActions rooms, MessageActions message,
                          AttendeeActions attendee) {
        this.user = user;
        this.message = message;
        this.e = events;
        this.room = rooms;
        this.attendee = attendee;
    }

    /**
     * Sends a message to a user
     * @param content the message to be sent
     * @param receiver the user who will be getting the message
     * @param sender the user who is sending the message
     * @return boolean true if message was successfully sent, false if it was not
     * */
    public boolean sendMessage(String sender, String receiver, String content){
        if (user.findUserFromUsername(sender).getContactsList().contains(receiver)){
            message.createMessage(sender, receiver, content);
            return true;
        }
        return false;
    }

    /**
     * Adds a user to the contact list of another user
     * @param toMe the user who's contact list that is updated
     * @param addMe the user who will be added
     * @return boolean true if contact was successfully added, false if it was not
     * */
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

    /**
     * Shows the messages from one user to another
     * @param toMe the user receiving the messages
     * @param fromMe the user who is sending the messages
     * @return array of a list of messages
     * */
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

    /**
     * Shows the contacts of a user
     * @param userid the user who wants to see their contacts
     * @return array of other usernames who are in their contacts
     * */

    public ArrayList<String> viewContacts (String userid){
        ArrayList<String> contacts = new ArrayList<String>();
        List<String> usersList = user.findUserFromId(userid).getContactsList();
        for (String id:usersList){
            contacts.add(user.findUserFromId(userid).getUsername());
        }
        return contacts;
    }

    //edited so that presenter can print fail messages based on why user cannot attend event

    /**
     * Adds an event to a user and an attendee to an event
     * @param event the event the user wants to attend
     * @param user the attendee who wants to attend an event
     * @return list of booleans if users can attend event or not
     * */

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
    }

    /**
     * Shows the events a given user is attending
     * @param user the user who wants to see their events
     * @return string of events that an user is attending
     * */

    public List<Event> viewOwnSchedule(String user){
        User a1 = attendee.usersHashMap.get(user);
        List<Event> eventS = new ArrayList<>();

        for (int i = 0; i < a1.getEventList().size(); i++){
            eventS.add(e.getEvents().get(a1.getEventList().get(i)));
        }

        return eventS;
    }

    /**
     * Shows the events a given user could be attending
     * @param user the user who wants to see possible events to attend
     * @return string of events that an user could attend
     * */

    public List<String> viewAvailableSchedule(String user){
        User a1 = attendee.usersHashMap.get(user);

        Set<String> allEvents = e.getEvents().keySet();
        List<String> availableS = new ArrayList<>();
        List<String> targetList = new ArrayList<>(allEvents);

        for (String s: targetList){
            if (!(checkConflictTime(user, s) && checkConflictSpots(s))){
                availableS.add(s);
            }
        }
        return availableS;
    }

    /**
     * Shows the spots available in an event
     * @param event the event that is given
     * @return int of the number of spots available
     * */

    public int spotsAvailable(String event){
        String rooms = e.getEvent(event).getRoomID();

        Room r1 = room.returnHashMap().get(rooms);

        return r1.getCapacity() - e.getEvent(event).getAttendees().size();

    }

    /**
     * Checks if users can attend an event or not
     * @param username the user who wants to see if they can attend an event
     * @param event the event the user wants to attend
     * @return boolean if user can attend event
     * */

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
    }

    /**
     * Checks if event is full or not
     * @param event the event that is being checked
     * @return boolean if event is full
     * */
    private boolean checkConflictSpots(String event){
        return spotsAvailable(event) == 0;

    }

    /**
     * To be overrided by OrganizerController
     * @param eventID the event to be cancelled
     * @return boolean if event was cancelled or not
     * */
    public boolean cancelEvent(String eventID){
        return false;
    }

    /**
     * To be overrided by AttendeeController
     * @param event the event to be removed
     * @param username the user who wants to leave the event
     * @return boolean if event was removed or not
     * */
    public boolean leaveEvent(String event, String username){
        return false;
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

