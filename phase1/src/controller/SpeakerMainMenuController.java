package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.SpeakerMessagePresenter;

import java.util.Scanner;

/**
 * A controller class for speaker that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerMainMenuController extends MainMenuController{
    private SpeakerController controller;
    private User user;
    private SpeakerMessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     * @param user the user
     * @param speakerController the controller responsible for speaker
     */
    public SpeakerMainMenuController(User user, SpeakerController speakerController){
        super(user, speakerController);
        this.displayMessage = new SpeakerMessagePresenter();
        this.displayEvent = new EventPresenter();
        this.user = user;
        this.controller = speakerController;
    }
    /**
     * Responds to menu option 2
     */
    public void option2(){
        displayMessage.printMenu();
        String option = scan.nextLine();
        SpeakerMessageMenuController menuController = new SpeakerMessageMenuController(this.controller);
        if (option.equals("1")){
            menuController.option1();
        }
        if (option.equals("2")){
            super.option2();
        }
    }
    /**
     * Responds to menu option 6
     */
    public void option6(){
      displayEvent.displayEvents(controller.viewOwnSchedule(user.getUsername()));
    }
}
