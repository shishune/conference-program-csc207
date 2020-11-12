package presenter;

/**
 * A presenter class. The AccountPresenter is responsible for anything related to logging in,
 * logging out, and navigating through the account.
 * @author Cynthia
 * @version 1
 * */
abstract class AccountPresenter {
    String basicMenu = ""
            + "[1] Log out"
            + "[2] Send a message"
            + "[3] View all messages"
            + "[4] Add a contact"
            + "[5] View all contacts";

    /**
     * An abstract class. Prints the main menu
     * */
    public abstract void printMainMenu();

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
}
