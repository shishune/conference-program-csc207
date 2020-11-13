package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.MessagePresenter;

import java.util.Scanner;

public class AttendeeMainMenuController extends MainMenuController{
    private UserController controller;
    private AttendeeController aController;
    private User user;
    private MessagePresenter displayMessage;
    private EventPresenter eventPresenter;
    private Scanner scan = new Scanner(System.in);
    public AttendeeMainMenuController(User user, UserController controller, AttendeeController attendeeController){
        super(user, controller);
        this.aController = attendeeController;
        this.displayMessage = new MessagePresenter();
    }
    public void option2(){

    }
    public void option6(){

    }
    public void option7(){

    }
    public void option8(){

    }
    public void option9(){

    }
}
