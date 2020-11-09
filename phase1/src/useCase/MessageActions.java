package useCase;

import entities.Event;
import gateway.LoadUp;
import entities.Message;

import java.util.HashMap;
import java.util.List;

// TODO: Add appropriate return types for methods
// TODO: Add all required methods

public class MessageActions {
    private List<String> conversations = null;
    private List<String> userMessages = null;

    public HashMap<String, Message> messages;

    private LoadUp loader = new LoadUp(); // this is okay because IGateway

    /** gets list of messages from the IGateway **/
    private void getAllConversations() {
        conversations = loader.getMessagesList();
    }

    /** gets list of all messages between two users **/
    private void getMessages(String senderId, String receiverId) {
        // might not be necessary
    }

    /** Gets a ___ of users for the speaker, organizer and attendee messaging actions
     **/
    private void getUsers() {

    }
    public void createMessage() {
        // TODO: Set return type to Message
    }


    //=======================================

    /**
     * Send message to a specific user
     **/
    public void sendMessage(String senderId, String receiverId) {

    }

    /**
     * Send messages to multiple users
     **/
    public void sendMessage(String senderId, List<String> receiverIds) {
        // check senderId for permission to send to multiple users
    }

    public void sendMessageToEvent() {

    }

    /**
     * Returns nested array of all conversations for one user
     **/
    public void printMessages(String senderId) {
        // presenter should call this method and turn array into output
    }

    /**
     * Returns an array of conversation between one user and another user
     **/
    public void printMessages(String senderId, String receiverId) {
        // presenter should call this method and turn array into output
    }


}
