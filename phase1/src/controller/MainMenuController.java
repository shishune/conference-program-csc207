package controller;
import presenter.*;
import entities.User;

import java.util.Scanner;

public class MainMenuController {
    private UserController controller;
    private OrganizerController oController;
    private SpeakerController sController;
    private AttendeeController aController;
    private User user;
    private MessagePresenter displayMessage = new MessagePresenter();
    private EventPresenter eventPresenter = new EventPresenter();
    private Scanner scan = new Scanner(System.in);


    public MainMenuController(User user, UserController controller){
        this.controller = controller;
        this.user = user;
        this.eventPresenter = new EventPresenter();
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

    }
    public void option7(){

    }
    public void option8(){

    }
    public void option9(){

    }
    public void option10(){

    }
}
