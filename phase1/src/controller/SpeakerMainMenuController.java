package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.SpeakerMessagePresenter;

import java.util.Scanner;

public class SpeakerMainMenuController extends MainMenuController{
    private SpeakerController sController;
    private SpeakerMessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);
    public SpeakerMainMenuController(User user, UserController controller, SpeakerController speakerController){
        super(user, controller);
        this.sController = speakerController;
        this.displayMessage = new SpeakerMessagePresenter();
        this.displayEvent = new EventPresenter();
    }
    public void option2(){
        displayMessage.printMenu();
        String option = scan.nextLine();
        SpeakerMessageMenuController menuController = new SpeakerMessageMenuController(this.sController);
        if (option.equals("1")){
            menuController.option1();
        }
        if (option.equals("2")){
            super.option2();
        }
    }
    public void option6(){
        displayEvent.displayEvents(controller.getOwnEvents());
    }
}
