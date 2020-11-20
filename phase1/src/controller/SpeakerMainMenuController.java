package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.SpeakerMessagePresenter;

import java.util.*;

import entities.Room;
import entities.Speaker;
import entities.User;
import entities.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import presenter.EventPresenter;
import presenter.MessagePresenter;
import presenter.SpeakerMessagePresenter;
import useCase.EventActions;
import useCase.SpeakerActions;
import useCase.RoomActions;

import java.util.Scanner;

/**
 * A controller class for speaker that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerMainMenuController extends MainMenuController {
    private SpeakerController controller;
    private User user;
    private SpeakerMessagePresenter displayMessage;
    private EventPresenter displayEvent;
    private Scanner scan = new Scanner(System.in);

    /**
     * Instantiates the main menu responder object
     *
     * @param user              the user
     * @param speakerController the controller responsible for speaker
     */
    public SpeakerMainMenuController(User user, SpeakerController speakerController) {
        super(user, speakerController);
        this.user = user;
        this.controller = speakerController;
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
     * Responds to menu option 3
     */
    public void option3() {
        //displayEvent.displayEvents(controller.viewAvailableSchedule(user.getUsername()));
        System.out.println("Option 3");
        HashMap<String, User> receiverHash = controller.returnUserIDHashMap();
        for(Map.Entry<String, User> entry : receiverHash.entrySet()) {
            System.out.println("Loop");
            displayMessage.displayMessages(controller, user.getId(), entry.getKey());
        }
    }

    /**
     * Responds to menu option 6
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

    public void option5() { //view all contacts
        displayMessage.displayContacts(controller, user.getId());
    }

    public void option6() {
        displayEvent.viewall();
        displayEvent.displayEvents(controller.viewAvailableSchedule(user.getUsername()));
    }




    }