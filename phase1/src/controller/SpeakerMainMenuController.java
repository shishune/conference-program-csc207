package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.SpeakerMessagePresenter;

import java.util.List;
import java.util.Scanner;
import entities.Room;
import entities.Speaker;
import entities.User;
import entities.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        this.user = user;
        this.controller = speakerController;
        this.displayMessage = new SpeakerMessagePresenter();
        this.displayEvent = new EventPresenter();
        this.user = user;
        this.controller = speakerController;
    }
    /**
     * Responds to menu option 3
     */
    public void option3() {
        displayEvent.displayEvents(controller.viewAvailableSchedule(user.getUsername()));
    }
    /**
     * Responds to menu option 6
     */


    public void option6() {
        displayEvent.viewall();
        displayEvent.displayEvents(controller.viewAvailableSchedule(user.getUsername()));
    }




    }