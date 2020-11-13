package controller;
import presenter.MessagePresenter;
import presenter.EventPresenter;
import entities.User;
import java.util.List;
import java.util.Scanner;

public class MainMenuController {
    private UserController controller;
    private User user;
    private MessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);


    public MainMenuController(User user, UserController controller){
        this.controller = controller;
        this.user = user;
        this.displayMessage = new MessagePresenter();
        this.displayEvent = new EventPresenter();
    }
    public void option3(){
        displayMessage.displayMessages(controller, user.getId(), user.getId());
    }
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
    public void option5(){ //view all contacts
        displayMessage.displayContacts(controller,user.getId());
    }
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
    public void option6(){
        displayEvent.promptAddEvent();
        String event = scan.nextLine();
        List<Boolean> checks = controller.signupEvent(event, user.getId());
        if(checks.size()==1){
            if (checks.get(0)==true){
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
    public void option7(){
        displayEvent.promptCancelEvent();
        String event = scan.nextLine();
        // don't need to pass an id to cancel event
        if(controller.cancelEvent(event, user.getId())){
            displayEvent.successCancelEnrol();
        }
        else{
            displayEvent.failedCancelEvent();
        }
    }

    //user has different method names so i changed it. Also, can we discuss the returns, because we're returning a list, we're returning a string represenation of the list.
    //Anyways idk who's working on this but message me and i'll change it since idk if ur done yet - jiessie
    public void option8(){
        displayEvent.displayEvents(controller.viewAvailableSchedule();
    }
    public void option9(){
        displayEvent.displayEvents(controller.viewOwnSchedule());
    }
}
