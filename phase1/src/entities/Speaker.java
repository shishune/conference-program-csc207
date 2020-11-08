package entities;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Speaker extends User{
    private String speakerId;
    //private static String lastSpeakerIdNums = "";

    public Speaker(String speakerId,String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin, boolean isOrganizer){
        super(speakerId, username, password, contactsList, eventList, isLogin, isOrganizer);
    }


    /**
     * Getter for the id unique to this speaker.
     * @return the id of this speaker
     * */
    public String getId() {
        return speakerId;
    }
}

    /*@Override
    public String generateId(){
        String id = "S";
        String lastSpeakerIdNumsSuffix;

        if (lastSpeakerIdNums.equals("")) {
            id = id + "0000000000000000";
            lastSpeakerIdNums = "0000000000000000";
        } else if (lastSpeakerIdNums.equals("0000000000000000")) {
            id = id + "0000000000000001";
            lastSpeakerIdNums = "0000000000000001";
        } else {
            lastSpeakerIdNumsSuffix = lastSpeakerIdNums.replaceAll("^[0]*", "");
            System.out.println(lastSpeakerIdNumsSuffix);

            int currAttendeeIdNumsSuffix = Integer.parseInt(lastSpeakerIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currAttendeeIdNumsSuffix).length(); i++) {
                id += "0";
            }
            id += currAttendeeIdNumsSuffix;
            lastSpeakerIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastSpeakerIdNums);
        return id;
    }*/

//edited it to fit the new super constructor - Jiessie
