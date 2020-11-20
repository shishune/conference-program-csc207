package controller;
import presenter.MessagePresenter;
import presenter.EventPresenter;
import entities.User;

import java.util.*;

/**
 * A controller class that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class MainMenuController extends AccountController{
    private UserController controller;
    private User user;
    private MessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     * @param user the user
     * @param controller the controller responsible for user
     */
    public MainMenuController(User user, UserController controller){
        this.controller = controller;
        this.user = user;
        this.displayMessage = new MessagePresenter();
        this.displayEvent = new EventPresenter();
    }

    /**
     * Responds to menu option 2
     */
    public void option2(){
        displayMessage.promptRecipient(); // enter user you would like to send message to
        String receiver = scan.nextLine();
        displayMessage.promptMessage(); // enter the message
        String content = scan.nextLine();
        //HashMap<String, User> userNames = controller.returnUsernameHashMap();
        //String receiverId = userNames.get(receiver).getId();
        System.out.println("RECEIVER: " + receiver);
        System.out.println("USER: " + user.getUsername());
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
        System.out.println("MAIN OPT 3");
        List<String> contactIds = user.getContactsList();
        //System.out.println("USER ID HASH: " + controller.returnUserIDHashMap());
        HashMap<String, User> userIdHash = controller.returnUserIDHashMap();
        ArrayList<String> contactUsernames = new ArrayList<String>();
        for(Map.Entry<String, User> user : userIdHash.entrySet()) {
            if(contactIds.contains(user.getKey())){
                contactUsernames.add(user.getValue().getUsername());
            }
        }
        //System.out.println("Contact names: " + contactUsernames);
        displayMessage.promptSelectReceiver(); // please select the receiver whose conversation you would like to view
        for(String username : contactUsernames){
            displayMessage.printString(username); // receiver username
        }
        String receiverUsername = scan.nextLine();
        System.out.println(controller.returnUserIDHashMap());
        HashMap<String, User> usernameHash = controller.returnUserUsernameHashMap();
        //System.out.println(controller);
        //System.out.println(user.getId());
        //System.out.println("USER HASH: " + usernameHash);
        //System.out.println(receiverUsername + "%%");
        //System.out.println(usernameHash.get(receiverUsername));
        if(usernameHash.get(receiverUsername) != null){
            displayMessage.displayMessages(controller, user.getId(), usernameHash.get(receiverUsername).getId()); // will pass in id instead of username
        } else {
            displayMessage.failedContact();
        }
    }

    /**
     * Responds to menu option 4
     */
    public void option4(){
        displayMessage.promptContact();
        String add = scan.nextLine();
        System.out.println(user.getContactsList());
        //HashMap<String, User> userUsernameHashMap = controller.returnUserUsernameHashMap();
        if (controller.returnUserUsernameHashMap().containsKey(add)){
            if (controller.addContact(add, user.getUsername())){
                displayMessage.successContact();
            }
        }
        else{
            displayMessage.failedContact();
        }
        //System.out.println(user.getContactsList());
    }

    /**
     * Responds to menu option 5
     */
    public void option5(){ //view all contacts
        displayMessage.displayContacts(controller,user.getId());
    }

    /**
     * Responds to menu option 2
     */
    /**public void option2(){
        displayMessage.promptRecipient();
        String receiver = scan.nextLine();
        displayMessage.promptMessage();
        String content = scan.nextLine();

        if (controller.sendMessage(user.getId(),receiver, content)){
            displayMessage.successMessage();
        }
        displayMessage.failedMessage();
    }**/

    /**
     * Responds to menu option 6
     */
    public void option6(){
        option8();
        displayEvent.promptAddEvent();
        String event = scan.nextLine();
        List<Boolean> checks = controller.signupEvent(event, user.getUsername());
        if(checks.size()==1){
            if (checks.get(0)){
                displayEvent.successAddEvent();
            }
            else{
                displayEvent.failedNoSuchEvent();
            }
        }
        else{
            if (!checks.get(1)){
                displayEvent.failedRoomFull();
            }
            else if(checks.get(2)){
                displayEvent.failedAttendeeTimeConflict();
            }
            else{
                displayEvent.failed();
            }
        }


    }

    /**
     * Responds to menu option 7
     */
    public void option7(){
        option9();
        displayEvent.promptCancelEvent();
        String eventName = scan.nextLine();
        // i think this is trying to cancel event for an attendee, so it's using leaveEvent in AttendeeActions
        if(controller.leaveEvent(eventName, user.getId())){
            displayEvent.successCancelEnrol();
        }
        else{
            displayEvent.failedCancelEvent();
        }
    }

    /**
     * Responds to menu option 8
     */
    public void option8(){
        displayEvent.displayEvents(controller.viewAvailableSchedule(user.getUsername()));
    }

    /**
     * Responds to menu option 9
     */
    public void option9(){
        displayEvent.displayEvents(controller.viewOwnSchedule(user.getUsername()));
    }
    public void option10(){}
}
