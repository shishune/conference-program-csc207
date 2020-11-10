package entities;
import java.util.ArrayList;

/**
 * An entity class. The Room object represents a room with a room ID attribute.
 * @author Cynthia
 * @version 1
 * */
public class Room {
    private String roomId;
    private int capacity;

    /**
     * instantiates a new Room object with an id attribute
     * @param roomId the unique id of this room
     */
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