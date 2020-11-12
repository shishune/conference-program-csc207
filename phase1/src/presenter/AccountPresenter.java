package presenter;

/**
 * A presenter class. The AccountPresenter is responsible for anything related to logging in,
 * logging out, and navigating through the account.
 * @author Cynthia
 * @version 1
 * */
public class AccountPresenter {
    String basicMenu = ""
            + "[1] Log out"
            + "[2] Send a message"
            + "[3] View all messages"
            + "[4] Add a contact"
            + "[5] View all contacts";

    /**
     * An abstract class. Prints the main menu
     * */
    public void printMainMenu(){
        System.out.println(basicMenu);
    }

    /**
     * Prompt for username
     * */
    public void promptUsername(){
        System.out.println("Please enter your username.");
    }

    /**
     * Prompt for password
     * */
    public void promptPassword(){
        System.out.println("Please enter your password.");
    }

    public void failedLogin(){
        System.out.println("Either your username or password is incorrect.");
    }

    /**
     * Prompt to return to menu
     * */
    public void promptReturn(){
        System.out.println("If you would like to return to the menu, enter any key. Note: " +
                "You must log out of your account in order to save new information. This is " +
                "accessible in the main menu.");
    }

    /**
     * A message informing user of successful logout; option to log back in or exit the program.
     * */
    public void successLogout(){
        System.out.println("You have successfully logged out. Enter any key to log into another account;" +
                "Enter 'X' to exit the program.");
    }

    /**
     * A message informing user that the menu item number does not exist
     * */
    public void printMenuError() {
        System.out.println("This number is not an option, please enter another number.");
    }
}
