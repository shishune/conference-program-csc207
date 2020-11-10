package entities;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Speaker extends User{
    private String speakerId;

    public Speaker(String speakerId,String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin, boolean isOrganizer){
        super(speakerId, username, password, contactsList, eventList, isLogin, isOrganizer);
    }


    /**
     * Getter for the id unique to this speaker.
     * @return the id of this speaker
     * */
    public String getId() {
        return userId;
    }
}
