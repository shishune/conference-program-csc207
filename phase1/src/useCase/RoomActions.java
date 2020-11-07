package useCase;
import java.util.HashMap;
import java.util.List;

import entities.*;
import useCase.GenerateID;

public class RoomActions {
    private HashMap<String, Room> roomsList; //TODO i feel like this should be private - isnt it a good design practice to encapsulate as much as possible? will think over later

    //TODO i feel like this use case needs a constructor...otherwise how would we access the same hashmap unless it is static (and instructors strongly advice against static variables) - will think over later though, not sure
    // (can also delete this constructor later. i was thinking that we may have to read in a csv/database via controller/gateway and store it in this hashmap)
    public RoomActions(HashMap<String, Room> roomsList){
        this.roomsList = roomsList;
    }

    public boolean addRoom(Room room){

        if (roomsList.containsKey(room.getRoomId())){
            return false;
        }
        roomsList.put(room.getRoomId(), room);
        return true;
    }

    public boolean removeRoom(Room room){
        if (roomsList.containsKey(room.getRoomId())){
            roomsList.remove(room.getRoomId(), room);
            return true;
        }
        return false;
    }




}
