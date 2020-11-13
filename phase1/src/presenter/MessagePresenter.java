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
    /**
     * Dislay messages
     * */
    public void displayMessages (UserController controller, String fromMe, String toMe) {
        ArrayList<ArrayList<String>> messagesList = controller.viewMessages(fromMe, toMe);
        int count = 1;
        for (ArrayList<String> message: messagesList){
            System.out.println(count);
            count++;
            System.out.println("Sender: "+message.get(0));
            System.out.println("Recipient: "+message.get(1));
            System.out.println("Message: "+message.get(2));
            System.out.println("\n");
        }
    }
    /**
     * Prompt for recicient
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
     * Message informing message sent
     * */
    public void successMessage(){
        System.out.println("Your message has been sent succesfully!");
    }

    /**
     * Display all contacts
     * */
    public void displayContacts(User user){
        List<String> usersList = user.getContactsList();
        int count = 1;
        for (String name:usersList){
            System.out.println(count+". "+name);
        }
    }

    /**
     * Message informing failed message
     * */
    public void failedMessage(){
        System.out.println("Your message could not be sent. Please check if the recipient is valid.");
    }
}
