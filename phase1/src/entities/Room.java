package entities;
import java.util.ArrayList;

public class Room {
    private static String lastRoomIdNums = "";
    private String roomId;
    private int capacity;
    //private ArrayList<Event> eventList;

    public Room(){ /*ArrayList<Event> eventList*/
        //this.eventList = eventList;
        this.capacity = 2;
        this.roomId = generateRoomId();
    }

    /**
     * Getter for the list of events held in this room.
     * @return list of events

    public ArrayList<Event> getEventList(){ return eventList; }
     * */

    /**
     * Setter for the list of events held in this room.
     * (The use case class should be responsible for checking/adding/removing events using this method.)
     * @param eventList list of events
    public void setEventList(ArrayList<Event> eventList){
        this.eventList = eventList;
    }
    * */


    /**
     * Getter for the capacity of this room.
     * @return capacity of room
     * */
    public int getCapacity(){ return capacity; }

    /**
     * Setter for the capacity of this room.
     * @param capacity list of events
     * */
    public void setCapacity(int capacity){ this.capacity = capacity; }

    /**
     * Getter for the id unique to this room.
     * @return the id of this room
     * */
    public String getRoomId(){ return roomId; }

    /**
     * TODO: there's a lot of copy and pasting for this method code, maybe make it a separate class?
     * Generates a unique id for this room.
     * @return the generated id for this room
     * */
    private String generateRoomId() {
        // TODO: Change to implement java.io (DUE NOV 03)
        String id = "R";
        String lastRoomIdNumsSuffix;

        if (lastRoomIdNums.equals("")) {
            id = id + "0000000000000000";
            lastRoomIdNums = "0000000000000000";
        } else if (lastRoomIdNums.equals("0000000000000000")) {
            id = id + "0000000000000001";
            lastRoomIdNums = "0000000000000001";
        } else {
            lastRoomIdNumsSuffix = lastRoomIdNums.replaceAll("^[0]*", "");
            System.out.println(lastRoomIdNumsSuffix);

            int currRoomIdNumsSuffix = Integer.parseInt(lastRoomIdNumsSuffix) + 1;

            for (int i = 0; i < 16 - Integer.toString(currRoomIdNumsSuffix).length(); i++) {
                id += "0";
            }
            id += currRoomIdNumsSuffix;
            lastRoomIdNums = id.substring(1);
        }
        System.out.println(id);
        System.out.println(lastRoomIdNums);
        return id;
    }



}

// TODO (think over later):
//  1) i don't think the room object needs the list of events at all. The Event object already depends on Room.
//  None of our use case classes even use Room
//  For now I'll keep the eventList instance variable as a comment.
//  2) Even if we do need eventList we need to store it as strings/ints not objects, which is complicated and
//  best handled by the Event object.