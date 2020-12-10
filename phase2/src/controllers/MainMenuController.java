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
    private String userID;
    private RoomActions room;
    private MessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private SpeakerActions speakerActions;
    private ConferenceActions conferenceActions;
    protected ConferencePresenter displayConference;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     * @param userID the user ID
     * @param controller the controller responsible for user
     */
    public MainMenuController(String userID, UserController controller, RoomActions room, SpeakerActions speakerActions, ConferenceActions conferenceActions){
        this.controller = controller;
        this.userID = userID;
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
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        displayMessage.promptRecipient(); // enter user you would like to send message to
        option5();
        String receiver = scan.nextLine();
        // if receiver in contacts
        displayMessage.promptMessage(); // enter the message
        String content = scan.nextLine();
        if (controller.sendMessage(username, receiver, content)){
            displayMessage.successMessage(); // message has been sent successfully
        } else {
            displayMessage.failedMessage(); // message could not be sent
        }
    }

    /**
     * Responds to menu option 3
     */
    public void option3(){
        List<String> contactIds = controller.returnUserIDHashMap().get(userID).getContactsList();
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
                displayMessage.displayMessages(controller, userID, usernameHash.get(receiverUsername).getId()); // will pass in id instead of username
            } else {
                displayMessage.failedContact();
            }
        }
    }

    /**
     * Responds to menu option 4
     */
    public void option4(){
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        displayMessage.promptContact();
        String add = scan.nextLine();
        if (controller.returnUserUsernameHashMap().containsKey(add)){
            if (controller.addContact(add, username)){
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

        displayMessage.displayContacts(controller,userID);

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
        if(controller.returnUserIDHashMap().get(userID).getEventList().contains(eventName)){
            if(controller.leaveEvent(eventName, userID)){
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
    public void viewEventsAccordingToConference(){
        String conferenceTitle = "";
        // TODO print list of users conferences
        // TODO have user choose which conference events they want to see
        ArrayList<List<String>> conferences = conferenceActions.returnConferences();
        displayConference.displayConferences(conferences);
        displayConference.promptConference();
        while(!conferenceActions.conferenceExists(conferenceTitle)){
            conferenceTitle = scan.nextLine();
        }
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewAvailableSchedule(username, conferenceTitle);

        if (eventsList.size() == 0){
            //displayMessage.noEvents();
            displayEvent.noEventsAvailable();
        } else {
            displayEvent.eventIntro();
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                List<String> speakerList = new ArrayList<String>();
                    for (String speaker : e.get(3).split(",")) {
                        if (speaker.equals("")){
                            speakerList.add(displayMessage.noSpeakers());
                        } else {
                            speakerList.add(speakerActions.findUserFromId(speaker).getUsername());
                        }
                }
                e.set(3, String.valueOf(speakerList));
                //e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
            }
            displayEvent.displayEvents(eventsList);
        }
    }

    /**
     * Responds to menu option 9- view events user is signed up for
     */
    public void option9(){
        String username = controller.returnUserIDHashMap().get(userID).getUsername();
        List<List<String>> eventsList = controller.viewOwnSchedule(username);
        if (eventsList.size() == 0){
            displayMessage.noEventsSignUp();
        } else {
            for (List<String> e : eventsList) {
                e.set(2, room.findRoomFromId(e.get(2)).getRoomName());
                if (e.get(3).equals("")){
                    e.set(3, "There are no speakers at the moment for this event.");
                }
                else{
                    e.set(3, speakerActions.findUserFromId(e.get(3)).getUsername());
                }
            }
            displayEvent.displayEvents(eventsList);
        }

    }

    /**
     * Responds to menu option 10
     */
    public void option10(){}

    public void option11(){}

    public void option12(){}

    public void option14(){}

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
    public void optionChangeCapacity() {}
}
