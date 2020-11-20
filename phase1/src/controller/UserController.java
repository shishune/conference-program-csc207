package controller;

import entities.*;
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
    private UserAccountActions user; // = getUserAccountActions();
    private MessageActions message; // = super.getMessages();
    private EventActions e;  //= super.getEvents();
    private RoomActions room; // = super.getRooms();
    private AttendeeActions attendee; // = super.getAttendees();
    private OrganizerActions organizer; // = super.getOrganizers();
    private SpeakerActions speaker; // = super.getSpeakers();
    //private HashMap<String, User> usernameHashmap = new HashMap<String, User>();
    private HashMap<String, User> userIdHashmap = new HashMap<String, User>();

    /**
     * Instantiates a new UserController object. Creates an instance of UserAccountActions, MessageActions, EventActions
     * AttendeeActions, RoomActions.
     * */
    public UserController(EventActions events, RoomActions rooms, MessageActions message, UserAccountActions user) {
        this.message = message;
        this.e = events;
        this.room = rooms;
        this.user = user;
        // this.user = user;
//        this.attendee = attendee;
//        this.organizer = organizer;
//        this.speaker = speaker;
    }
    //alternate constructor to access methods that do not need so many parameters
//    public UserController(){};
    public HashMap<String, User> returnUserUsernameHashMap() {
            HashMap<String, User> usernameHashmap = new HashMap<String, User>();
            System.out.println("RETURN USER HASH");
            System.out.println("Attendee: " + attendee);
            System.out.println("Organizer: " + organizer);
            System.out.println("Speaker: " + speaker);
            System.out.println("User: " + user);
            System.out.println("Hash: " + attendee.returnUsernameHashMap());
            System.out.println("EMPTY?: " + attendee.returnUsernameHashMap().isEmpty());
        if (!(attendee == null) && !attendee.returnUsernameHashMap().isEmpty()){
            System.out.println(attendee.returnUsernameHashMap());
            usernameHashmap.putAll(attendee.returnUsernameHashMap());
            System.out.println(usernameHashmap);
        }
        if (!(organizer == null) && !organizer.returnUsernameHashMap().isEmpty()){
            usernameHashmap.putAll(organizer.returnUsernameHashMap());
        }
        if (!(speaker == null) && !speaker.returnUsernameHashMap().isEmpty()){
            usernameHashmap.putAll(speaker.returnUsernameHashMap());
        }
//        if (!(user == null) && !user.returnUsernameHashMap().isEmpty()){
////            usernameHashmap.putAll(speaker.returnSpeakerUsernameHashMap());
////        }
        System.out.println("END USERR HASH");
        return usernameHashmap;

    }



    public HashMap<String, User> returnUserIDHashMap(){
        if (!attendee.returnIDHashMap().isEmpty()){
            userIdHashmap.putAll(attendee.returnIDHashMap());
        }
        if (!organizer.returnIDHashMap().isEmpty()){
            userIdHashmap.putAll(organizer.returnIDHashMap());
        }
        if (!speaker.returnIDHashMap().isEmpty()){
            userIdHashmap.putAll(speaker.returnIDHashMap());
        }
        return userIdHashmap;
    }

    /**
     * Sends a message to a user
     * @param content the message to be sent
     * @param receiver the user who will be getting the message
     * @param sender the user who is sending the message
     * @return boolean true if message was successfully sent, false if it was not
     * */
    public boolean sendMessage(String sender, String receiver, String content) {
        if (user != null) {
            //System.out.println(organizer.findUserFromUsername(sender));
            //System.out.println(organizer.findUserFromUsername(sender).getContactsList());
            //System.out.println(organizer.findUserFromUsername(sender).getContactsList().contains(receiver));
            if (true) { // temp if condition, fill in parentheses later
                HashMap<String, User> usernameHashMap = returnUserUsernameHashMap();
                System.out.println(usernameHashMap);
                System.out.println(usernameHashMap.get(sender));
                System.out.println(usernameHashMap.get(sender).getId());
                String senderId = usernameHashMap.get(sender).getId();
                String receiverId = usernameHashMap.get(receiver).getId();
                message.createMessage(senderId, receiverId, content);
                return true;
            }
        } else {
            System.out.println("User does not exist");
        }
        return false;
    }

    /**
     * Adds a user to the contact list of another user
     * @param toMe the username who's contact list that is updated
     * @param addMe the username who will be added
     * @return boolean true if contact was successfully added, false if it was not
     * */
    public boolean addContact(String addMe, String toMe) { // toMe should be a username
        if (user != null) {
            User me = user.findUserFromUsername(toMe);
            System.out.println(me.getContactsList());
            return user.addUserContactList(toMe, addMe);
        }
        return false;
    }

    public boolean deleteContact(String removeMe, String toMe){
        return attendee.removeUserContactList(toMe, removeMe)
                ? attendee.removeUserContactList(toMe, removeMe)
                : organizer.removeUserContactList(toMe, removeMe)
                ? organizer.removeUserContactList(toMe, removeMe)
                : speaker.removeUserContactList(toMe, removeMe) && speaker.removeUserContactList(toMe, removeMe);
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
            info.add(attendee.findUserFromId(message.getSenderId()).getUsername() != null
                    ? attendee.findUserFromId(message.getSenderId()).getUsername()
                    : organizer.findUserFromId(message.getSenderId()).getUsername() != null
                    ? organizer.findUserFromId(message.getSenderId()).getUsername()
                    : speaker.findUserFromId(message.getSenderId()).getUsername() != null
                    ? speaker.findUserFromId(message.getSenderId()).getUsername()
                    : null);
            info.add(attendee.findUserFromId(message.getReceiverId()).getUsername() != null
                    ? attendee.findUserFromId(message.getReceiverId()).getUsername()
                    : organizer.findUserFromId(message.getReceiverId()).getUsername() != null
                    ? organizer.findUserFromId(message.getReceiverId()).getUsername()
                    : speaker.findUserFromId(message.getReceiverId()).getUsername() != null
                    ? speaker.findUserFromId(message.getReceiverId()).getUsername()
                    : null);
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

    public ArrayList<String> viewContacts (String userid) {
        ArrayList<String> contacts = new ArrayList<String>();
        if (user != null){
            List<String> usersList = user.findUserFromId(userid).getContactsList();
            for (String id : usersList) {
                contacts.add(attendee.findUserFromId(userid).getUsername() != null
                        ? attendee.findUserFromId(userid).getUsername()
                        : organizer.findUserFromId(userid).getUsername() != null
                        ? organizer.findUserFromId(userid).getUsername()
                        : speaker.findUserFromId(userid).getUsername() != null
                        ? speaker.findUserFromId(userid).getUsername()
                        : null);
            }
            // TODO i think theres something wrong with the for loop here
            return contacts;
        }
        return null;
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
        User a1 = returnUserUsernameHashMap().get(user);


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

    public List<List<String>> viewOwnSchedule(String user){
        User a1 = returnUserUsernameHashMap().get(user);
        List<String> eventList = a1.getEventList();
        List<List<String>> scheduleList = new ArrayList<List<String>>();
        for (String event: eventList){
            String title = e.getEvent(event).getTitle();
            String dateTime = e.getEvent(event).getDateTime();
            String roomId = e.getEvent(event).getRoomID();
            String speaker = e.getEvent(event).getSpeaker();
            List<String> info = new ArrayList<String>();
            info.add(title);
            info.add(dateTime);
            info.add(roomId);
            info.add(speaker);
            scheduleList.add(info);
        }
        return scheduleList;
    }

    /**
     * Shows the events a given user could be attending
     * @param user the user who wants to see possible events to attend
     * @return string of events that an user could attend
     * */

    public List<List<String>> viewAvailableSchedule(String user){
        Set<String> allEvents = e.getEvents().keySet();
        List<String> availableS = new ArrayList<>();
        List<String> targetList = new ArrayList<>(allEvents);

        for (String s: targetList){
            if (!(checkConflictTime(user, s) && checkConflictSpots(s))){
                availableS.add(s);
            }
        }
        List<List<String>> scheduleList = new ArrayList<List<String>>();
        for (String event: availableS){
            String title = e.getEvent(event).getTitle();
            String dateTime = e.getEvent(event).getDateTime();
            String roomId = e.getEvent(event).getRoomID();
            String speaker = e.getEvent(event).getSpeaker();
            List<String> info = new ArrayList<String>();
            info.add(title);
            info.add(dateTime);
            info.add(roomId);
            info.add(speaker);
            scheduleList.add(info);
        }
        return scheduleList;
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

    private boolean checkConflictTime(String username, String event) {
        //return true if there is a conflict
        String timeEvent = e.getEvent(event).getDateTime();
        if (attendee != null && organizer != null && speaker != null) {
            User u = attendee.findUserFromUsername(username) != null
                    ? attendee.findUserFromUsername(username)
                    : organizer.findUserFromUsername(username) != null
                    ? organizer.findUserFromUsername(username)
                    : speaker.findUserFromUsername(username) != null
                    ? speaker.findUserFromUsername(username)
                    : null;

            for (int i = 0; i < u.getEventList().size(); i++) {

                String name = u.getEventList().get(i);

                String time = e.getEvent(name).getDateTime();

                if (time.equals(timeEvent)) {
                    return true;
                }
            }

            return false;
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

    public boolean checkTimeConflict(String username, String dateTime) {
        //return true if there is a conflict
        //String timeEvent = e.getEvent(event).getDateTime();
        if (user != null) {

            User u = user.findUserFromUsername(username);

            if ((u != null) && u.getEventList() != null) {

                for (int i = 0; i < u.getEventList().size(); i++) {

                    String name = u.getEventList().get(i);

                    String time = e.getEvent(name).getDateTime();

                    if (time.equals(dateTime)) {
                        return true;
                    }
                }
            }

            return false;
        }
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

