package controller;
import controller.MainMenuController;
import controller.SpeakerController;
import controller.SpeakerMessageMenuController;
import entity.Event;
import entity.User;
import presenter.EventPresenter;
import presenter.SpeakerMessagePresenter;
import useCase.AttendeeActions;
import useCase.EventActions;
import useCase.RoomActions;
import useCase.SpeakerActions;

import java.util.*;

/**
 * A controller class for speaker that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerMainMenuController extends MainMenuController {
    private controller.SpeakerController controller;
    private User user;
    private SpeakerMessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private EventActions eventActions;
    private AttendeeActions attendeeActions;
    private RoomActions roomActions;
    private SpeakerActions speakerActions;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     *
     * @param user              the user
     * @param speakerController the controller responsible for speaker
     */
    public SpeakerMainMenuController(User user, SpeakerController speakerController, EventActions eventActions, AttendeeActions attendeeActions, RoomActions roomActions, SpeakerActions speakerActions) {
        super(user, speakerController, roomActions, speakerActions);
        this.user = user;
        this.controller = speakerController;
        this.displayMessage = new SpeakerMessagePresenter();
        this.displayEvent = new EventPresenter();
        this.user = user;
        this.controller = speakerController;
        this.eventActions = eventActions;
        this.attendeeActions = attendeeActions;
        this.roomActions = roomActions;
    }

    /**
     * Responds to menu option 2
     */
    public void option2(){
        displayMessage.printMenu();
        String option = scan.nextLine();
        controller.SpeakerMessageMenuController menuController = new SpeakerMessageMenuController(this.controller);
        if (option.equals("1")){
            menuController.option1();
        }
        if (option.equals("2")){
            super.option2();
        }
    }
    /**
     * Responds to menu option 3
     */
    public void option3() {
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
     * Add a contact
     */

    public void option4(){
        displayMessage.promptContact();
        String add = scan.nextLine();
        //HashMap<String, User> userUsernameHashMap = controller.returnUserUsernameHashMap();
        if (controller.returnUserUsernameHashMap().containsKey(add)){
            if (controller.addContact(add, user.getUsername())){
                displayMessage.successContact();
            }
        }
        else{
            displayMessage.failedContact();
        }
    }

    /**
     * Responds to menu option 5
     */
    public void option5() { //view all contacts
        displayMessage.displayContacts(controller, user.getId());
}

    /**
     * Responds to menu option 6
     */
    public void option6() {
        List<String> e = user.getEventList();
        List<List<String>> stringE = new ArrayList<>();

        for (String event : e) {
            List<String> miniList = new ArrayList<>();
            Event e1 = eventActions.getEvent(event);

            List<String> newList = new ArrayList<>();
            for (String attendeeID : e1.getAttendees()) {
                newList.add(attendeeActions.findUserFromId(attendeeID).getUsername());
            }


            miniList.add((e1.getTitle()));
            miniList.add(e1.getDateTime());
            miniList.add(roomActions.findRoomFromId(e1.getRoomID()).getRoomName());
            miniList.add(newList.toString());

            stringE.add(miniList);
        }

        displayEvent.viewEvents(stringE);
    }
}