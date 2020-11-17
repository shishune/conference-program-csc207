package controller;
import presenter.MessagePresenter;
import presenter.EventPresenter;
import entities.User;
import java.util.List;
import java.util.Scanner;

/**
 * A controller class that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class MainMenuController {
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
     * Responds to menu option 3
     */
    public void option3(){
        displayMessage.displayMessages(controller, user.getId(), user.getId());
    }

    /**
     * Responds to menu option 4
     */
    public void option4(){
        displayMessage.promptContact();
        String add = scan.nextLine();
        if (controller.addContact(add, user.getId())){
            displayMessage.successContact();
        }
        else{
            displayMessage.failedContact();
        }
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
    public void option2(){
        displayMessage.promptRecipient();
        String receiver = scan.nextLine();
        displayMessage.promptMessage();
        String content = scan.nextLine();
        if (controller.sendMessage(user.getId(),receiver, content)){
            displayMessage.successMessage();
        }
        displayMessage.failedMessage();
    }

    /**
     * Responds to menu option 6
     */
    public void option6(){
        displayEvent.promptAddEvent();
        String event = scan.nextLine();
        List<Boolean> checks = controller.signupEvent(event, user.getId());
        if(checks.size()==1){
            if (checks.get(0)){
                displayEvent.successAddEvent();
            }
            else{
                displayEvent.failedNoSuchEvent();
            }
        }
        else{
            if (checks.get(1)){
                displayEvent.failedRoomFull();
            }
            else if(!checks.get(1)){
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
        displayEvent.promptCancelEvent();
        String event = scan.nextLine();
        // i think this is trying to cancel event for an attendee, so it's using leaveEvent in AttendeeActions
        if(controller.leaveEvent(event, user.getId())){
            displayEvent.successCancelEnrol();
        }
        else{
            displayEvent.failedCancelEvent();
        }
    }

    //user has different method names so i changed it. Also, can we discuss the returns, because we're returning a list, we're returning a string represenation of the list.
    //Anyways idk who's working on this but message me and i'll change it since idk if ur done yet - jiessie
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
}
