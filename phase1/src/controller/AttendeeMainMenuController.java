package controller;
import entities.User;
import presenter.EventPresenter;
import presenter.MessagePresenter;

import java.util.Scanner;

public class AttendeeMainMenuController extends MainMenuController{
    public AttendeeMainMenuController(User user, UserController controller){
        super(user, controller);
    }
}
