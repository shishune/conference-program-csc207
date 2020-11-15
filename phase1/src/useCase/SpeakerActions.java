package useCase;

import entities.Attendee;
import entities.Speaker;
import entities.User;
import gateway.LoadUpIGateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SpeakerActions extends UserAccountActions {
    private LoadUpIGateway loader;
    private ArrayList<String> speakerLoadUpList = new ArrayList<String>();
    private HashMap<String, Speaker> speakers;

    public SpeakerActions(LoadUpIGateway loader) {
        // with message ID as key and message object as value
        this.loader = loader;
        getAllSpeakers(loader); // gets all messages from message.csv
        addLoadedToHashMap(); // adds those messages to a hashmap of all messages from the csv
    }
    // why does this return user instead of speaker type?
    public User createSpeaker(String userId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        Speaker userSpeaker = new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userSpeaker);
        return userSpeaker;
    }

    public User createSpeaker(String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin) {
        GenerateID generateId = new GenerateID(loader);
        String userId = "S" + generateId;
        Speaker userSpeaker = new Speaker(userId, username, password, contactsList, eventList, isLogin, false);
        // addUserToHashMap(userSpeaker);
        return userSpeaker;
    }

    /** Load new messages into HashMap of new messages **/
    public void loadSpeaker(String speakerId, Speaker newSpeaker){
        // This needs to update every collection we use for messages
        // i.e. if we add a new hashmap/array/etc. for some method, we need to add
        // otherHash.put(messageId, newMessage)
        // messageArray.add(newMessage)
        // etc. etc.
        speakers.put(speakerId, newSpeaker);
    }

    public HashMap<String, Speaker> returnSpeakerHashMap() {
        return speakers;
    }

    /** gets list of messages from the IGateway **/
    private void getAllSpeakers(LoadUpIGateway loader) {
        speakerLoadUpList = loader.getSpeakersList();
    }

    /** Adds messages loaded from the csv to <messages> **/
    private void addLoadedToHashMap() {
        if (speakerLoadUpList != null) {
            for(String speakerString : speakerLoadUpList) {
                String[] speakerInfo = speakerString.split(",");
                List<String> loadContactsList = Arrays.asList(speakerInfo[3].split("%%"));
                List<String> loadEventsList = Arrays.asList(speakerInfo[4].split("%%"));
                boolean loadIsLogin = Boolean.parseBoolean(speakerInfo[5]);
                boolean loadIsOrganizer = Boolean.parseBoolean(speakerInfo[6]);
                Speaker loadedMessage = new Speaker(speakerInfo[0], speakerInfo[1], speakerInfo[2], loadContactsList, loadEventsList, loadIsLogin, loadIsOrganizer);
                //Message loadedMessage = createMessage(messageInfo[0], messageInfo[1], messageInfo[2], messageInfo[3], messageInfo[4]);
                speakers.put(speakerInfo[0], loadedMessage);
            }
        }
    }

}
