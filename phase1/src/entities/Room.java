package entities;
import java.util.ArrayList;

public class Room {
    private static String lastRoomIdNums = "";
    private String roomId;
    private int capacity;

    public Room(String roomId){
        this.capacity = 2;
        this.roomId = roomId;
    }

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
}