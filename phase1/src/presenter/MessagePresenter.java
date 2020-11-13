package presenter;
import java.util.HashMap;
import java.util.List;
import entities.Message;
import entities.User;

public class MessagePresenter {
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
    public void promptRecipient(){
        System.out.println("To whom would you like to send this message? Enter a username.");
    }
    public void promptMessage(){
        System.out.println("Enter your message.");
    }
    public void successMessage(){
        System.out.println("Your message has been sent succesfully!");
    }
    public void displayContacts(List<User> usersList){
        int count = 1;
        for (User user:usersList){
            System.out.println(count+". "+user.getUsername());
        }
    }
}
