package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.MessagePresenter;
import presenter.OrganizerMessagePresenter;

import java.util.Scanner;

public class OrganizerMainMenuController extends MainMenuController{
    private UserController controller;
    private OrganizerController oController;
    private User user;
    private OrganizerMessagePresenter displayMessage;
    private EventPresenter eventPresenter;
    private Scanner scan = new Scanner(System.in);

    public OrganizerMainMenuController(User user, UserController controller, OrganizerController organizerController){
        super(user, controller);
        this.oController = organizerController;
        this.displayMessage = new OrganizerMessagePresenter();
    }
    public void option2(){
        displayMessage.printMenu();
        String option = scan.nextLine();
        OrganizerMessageMenuController menuController = new OrganizerMessageMenuController(this.oController);
        if (option.equals("1")){
            menuController.option1();
        }
        if (option.equals("2")){
            menuController.option2();
        }
        if (option.equals("3")){
            menuController.option3();
        }
        if (option.equals("4")){
            menuController.option4();
        }
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
