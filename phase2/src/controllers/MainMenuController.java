package controllers;
import entities.User;
import presenters.ConferencePresenter;
import presenters.EventPresenter;
import presenters.MessagePresenter;
import useCases.ConferenceActions;
import useCases.RoomActions;
import useCases.SpeakerActions;

import java.util.*;

/**
 * A controller class that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public abstract class MainMenuController extends AccountController {
    private controllers.UserController controller;
    private User user;
    private RoomActions room;
    private MessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private SpeakerActions speakerActions;
    private ConferenceActions conferenceActions;
    private ConferencePresenter displayConference;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     * @param user the user
     * @param controller the controller responsible for user
     */
    public MainMenuController(User user, UserController controller, RoomActions room, SpeakerActions speakerActions, ConferenceActions conferenceActions){
        this.controller = controller;
        this.user = user;
        this.displayMessage = new MessagePresenter();
        this.displayEvent = new EventPresenter();
        this.displayConference = new ConferencePresenter();
        this.room = room;
        this.speakerActions = speakerActions;
        this.conferenceActions = conferenceActions;
    }

    /**
     * Responds to menu option 2
     */
    public void option2(){
        displayMessage.promptRecipient(); // enter user you would like to send message to
        option5();
        String receiver = scan.nextLine();
        // if receiver in contacts
        displayMessage.promptMessage(); // enter the message
        String content = scan.nextLine();
        if (controller.sendMessage(user.getUsername(), receiver, content)){
            displayMessage.successMessage(); // message has been sent successfully
        } else {
            displayMessage.failedMessage(); // message could not be sent
        }
    }

    /**
     * Responds to menu option 3
     */
    public void option3(){
        List<String> contactIds = user.getContactsList();
        if(contactIds.isEmpty()){
            displayMessage.zeroContacts();
        } else {
            HashMap<String, User> userIdHash = controller.returnUserIDHashMap();
            ArrayList<String> contactUsernames = new ArrayList<String>();
            for(Map.Entry<String, User> user : userIdHash.entrySet()) {
                if(contactIds.contains(user.getKey())){
                    contactUsernames.add(user.getValue().getUsername());
                }
            }
            displayMessage.promptSelectReceiver(); // please select the receiver whose conversation you would like to view
            for(String username : contactUsernames){
                displayMessage.printString(username); // receiver username
            }
            String receiverUsername = scan.nextLine();
            HashMap<String, User> usernameHash = controller.returnUserUsernameHashMap();
            if(usernameHash.get(receiverUsername) != null){
                displayMessage.displayMessages(controller, user.getId(), usernameHash.get(receiverUsername).getId()); // will pass in id instead of username
            } else {
                displayMessage.failedContact();
            }
        }
    }

    /**
     * Responds to menu option 4
     */
    public void option4(){
        displayMessage.promptContact();
        String add = scan.nextLine();
        if (controller.returnUserUsernameHashMap().containsKey(add)){
            if (controller.addContact(add, user.getUsername())){
                displayMessage.successContact();
            } else {
                displayMessage.sameUserContact();
            }
        }
        else{
            displayMessage.failedContact();
        }
    }

    /**
     * Responds to menu option 5- view all contacts
     */
    public void option5(){ //view all contacts

        displayMessage.displayContacts(controller,user.getId());

    }

    /**
     * Responds to menu option 6- sign up for event
     */
    public abstract void option6();

    /**
     * Responds to menu option 7- cancel attendance to an event
     */
    public void option7(){
        option9();
        displayEvent.promptCancelEvent();
        String eventName = scan.nextLine();
        // i think this is trying to cancel event for an attendee, so it's using leaveEvent in AttendeeActions
        if(user.getEventList().contains(eventName)){
            if(controller.leaveEvent(eventName, user.getId())){
                displayEvent.successCancelEnrol();
            }
        }
        else{
            displayEvent.failedCancelEvent();
        }
    }

    /**
     * Responds to menu option 8- view all events
     */
    public void option8(){

        List<List<String>> eventsList = controller.viewAvailableSchedule(user.getUsername());
        if (eventsList.size() == 0){
            displayMessage.noEvents();
        } else {

            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
        }
    }

    /**
     * Responds to menu option 9- view events user is signed up for
     */
    public void option9(){
        List<List<String>> eventsList = controller.viewOwnSchedule(user.getUsername());
        if (eventsList.size() == 0){
            displayMessage.noEventsSignUp();
        } else {
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
        }

    }

    /**
     * Responds to menu option 10
     */
    public void option10(){}

    public void option11(){}

    public void option15(){}

    /***
     * Responds to menu option 13 - View conferences
     */
    public void option16(){
        displayConference.displayConferences(conferenceActions.returnConferences());
    }

    protected boolean validInput(String str){
        return !str.equals("") && !str.equalsIgnoreCase("x");
    }
}
