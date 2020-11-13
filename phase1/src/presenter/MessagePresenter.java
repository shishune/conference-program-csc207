package presenter;
import java.util.HashMap;
import java.util.List;
import entities.Message;
import entities.User;

/**
 * A presenter class. This class is responsible for anything related to displaying messages to the user.
 * @author Cynthia
 * @version 1
 * */
public class MessagePresenter {

    /**
     * Dislays messages
     * */
    public void displayMessages(List<Message> messagesList, HashMap<String, User> usersHashMap){
        int count = 1;
        for (Message message: messagesList){
            System.out.println(count);
            count++;
            System.out.println("Sender: "+usersHashMap.get(message.getSenderId()));
            System.out.println("Recipient: "+usersHashMap.get(message.getReceiverId()));
            System.out.println("Message: "+message.getMessage());
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
     * Message informing message sent
     * */
    public void successMessage(){
        System.out.println("Your message has been sent succesfully!");
    }

    /**
     * Display all contacts
     * */
    public void displayContacts(List<User> usersList){
        int count = 1;
        for (User user:usersList){
            System.out.println(count+". "+user.getUsername());
        }
    }
}
