package presenter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import entities.Message;
import entities.User;
import controller.UserController;

/**
 * A presenter class. This class is responsible for anything related to displaying messages to the user.
 * @author Cynthia
 * @version 1
 * */
public class MessagePresenter {

    // used in MainMenuController to print list of contacts for a user
    public void printString(String theString){
        System.out.println(theString);
    }

    public void promptSelectReceiver(){
        System.out.println("Please select the conversation you would like to view by selecting the receiver of your message.");
    }

    /**
     * Display messages
     * */
    public boolean displayMessages (UserController controller, String fromMe, String toMe) {
        ArrayList<ArrayList<String>> messagesList = controller.viewMessages(fromMe, toMe); // fromMe and toMe are are user IDs
        int count = 1;
        for (ArrayList<String> message: messagesList){
            System.out.println(count);
            count++;
            System.out.println("Sender: "+message.get(0));
            System.out.println("Recipient: "+message.get(1));
            System.out.println("Time Sent: "+message.get(2));
            System.out.println("Message: "+message.get(3));
            System.out.println("\n");
        }
        return true;
    }
    /**
     * Prompt for recipient
     * */
    public void promptRecipient(){
        System.out.println("To whom would you like to send this message? Enter a username.");
    }

    /**
     * Prompt for message content
     * */
    public void promptMessage(){
        System.out.println("Enter your message.");
    }

    /**
     * Prompt for contact
     * */
    public void promptContact(){ System.out.println("Enter the username of the person you would like to add to your contacts list.");}
    /**
     * Message of successful contact
     * */
    public void successContact(){
        System.out.println("You have successfully added this person to your contacts list!");
    }
    /**
     * Message of failed contact
     * */
    public void failedContact(){
        System.out.println("This username is not valid.");
    }

    /**
     * User does not have any contacts
     */
    public void zeroContacts() { System.out.println("You have not received any messages or contacted anyone."); }
    /**
     * Message of failed contact
     * */
    public void sameUserContact(){
        System.out.println("This username is your username");
    }
    /**
     * Message informing message sent
     * */
    public void successMessage(){
        System.out.println("Your message has been sent succesfully!");
    }

    /**
     * Display all contacts
     * */
    public void displayContacts(UserController controller, String userId){
        List<String> usersList = controller.viewContacts(userId);
        if(usersList.size() == 0){
            noContacts();
        } else {
            int count = 1;
            if (usersList != null) {
                for (String name : usersList) {
                    System.out.println(count + ". " + name);
                    count += 1;
                }
            }
        }
    }
    /**
     * Message informing user they have no contacts
     * */
    public void noContacts(){
        System.out.println("You currently have no contacts :(");
        System.out.println("Add a friend through option 4!");
    }

    /**
     * Message informing failed message
     * */
    public void failedMessage(){
        System.out.println("Your message could not be sent. Please check if the recipient is valid.");
    }

    /**
     * Message informing user is signed up for no events
     * */
    public void noEventsSignUp(){
        System.out.println("You are not signed up for any events.");
    }

    /**
     * Message informing user there are no events
     * */
    public void noEvents(){
        System.out.println("There are no events available.");
    }
}
