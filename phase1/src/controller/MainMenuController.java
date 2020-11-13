package controller;
import presenter.*;
import entities.User;

public class MainMenuController {
    private UserController controller;
    private User user;
    private MessagePresenter displayMessage = new MessagePresenter();
    private EventPresenter eventPresenter = new EventPresenter();

    public MainMenuController(User user, UserController controller){
        this.controller = controller;
        this.user = user;
    }
    public void option3(){
        controller.viewMessages(user.getId(), user.getId());
    }
    public void option4(){

    }
    public void option5(){

    }

    //todo idk what to do with these...cannot make them abstract
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
    public void option10(){

    }
}
