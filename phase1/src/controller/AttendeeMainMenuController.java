package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.MessagePresenter;

import java.util.Scanner;
/**
 * A controller class for attendee that decides what to do based on user input when choosing from the main menu.
 * @author Cynthia
 * @version 1
 * */
public class AttendeeMainMenuController extends MainMenuController{
    /**
     * instantiates this class referring to super class
     * @param user the user object
     * @param controller the controller responsible for user
     */
    public AttendeeMainMenuController(User user, UserController controller){
        super(user, controller);
    }
}
