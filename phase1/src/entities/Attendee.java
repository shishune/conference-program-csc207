package entities;

import java.util.ArrayList;
import java.util.List;

public class Attendee extends User{
 //   private static String lastAttendeeIdNums = "";

    public Attendee (String attendeeId, String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin, boolean isOrganizer){
        super(attendeeId, username, password, contactsList, eventList, isLogin, isOrganizer); //added eventList and userId to constructor (Jiessie)
    }

    /**
     * Getter for the id unique to this attendee.
     * @return the id of this attendee
     * */
    public String getId() {
        return userId;
    }

    /**
     * Return a unique generated AttendeeID
     * @return ID of an attendee
     * */
    /*public String generateId() {
        String id = "A";
        String lastAttendeeIdNumsSuffix;

        if (lastAttendeeIdNums.equals("")) {
            id = id + "0000000000000000";
            lastAttendeeIdNums = "0000000000000000";
        } else if (lastAttendeeIdNums.equals("0000000000000000")) {
            id = id + "0000000000000001";
            lastAttendeeIdNums = "0000000000000001";
        } else {
            lastAttendeeIdNumsSuffix = lastAttendeeIdNums.replaceAll("^[0]*", "");
            System.out.println(lastAttendeeIdNumsSuffix);

            int currAttendeeIdNumsSuffix = Integer.parseInt(lastAttendeeIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currAttendeeIdNumsSuffix).length(); i++) {
                id += "0";
            }
            id += currAttendeeIdNumsSuffix;
            lastAttendeeIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastAttendeeIdNums);
        return id;
    }*/

    // i moved it so the code is all together - Mizna ^-^

}
