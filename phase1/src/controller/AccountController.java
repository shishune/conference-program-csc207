package controller;
import gateway.LoadUp;
import gateway.LoadUpIGateway;
import useCase.*;
import presenter.*;
import java.util.Scanner;

/**
 * Navigates account; takes in user input
 */
public class AccountController {

    //just an idea to show how controllers interact with presenters; subject to change
    public void run(){
        LogIn logIn = new LogIn();
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        AccountPresenter accountMessage = new AccountPresenter();
        accountMessage.promptUsername();
        String username = scan.nextLine();  // Read user input
        accountMessage.promptPassword();
        String password = scan.nextLine();  // Read user input
        logIn.loggingIn(username, password); // evaluate username/password
        //etc.

    }

}
