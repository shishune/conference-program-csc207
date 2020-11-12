package presenter;

public class SpeakerAccountPresenter extends AccountPresenter{
    public void printMainMenu(){
        String display = ""
                + "[1] Send a message"
                + "[2] View all messages"
                + "[3] View your schedule of talks"
                + "Please select a menu item number.";
        System.out.println(display);
    }
}
