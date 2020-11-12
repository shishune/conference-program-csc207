package presenter;

/**
 * A presenter class. This class is responsible for anything related to logging in,
 * logging out, and navigating through the account. It also creates a menu specific to a speaker.
 * @author Cynthia
 * @version 1
 * */
public class SpeakerAccountPresenter extends AccountPresenter{

    /**
     * Prints the main menu
     * */
    public void printMainMenu(){
        String display = basicMenu
                + "[6] View your schedule of talks"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
