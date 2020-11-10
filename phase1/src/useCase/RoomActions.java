package useCase;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import entities.*;
import gateway.LoadUp;
import gateway.Store;
import useCase.GenerateID;

public class RoomActions {
    private HashMap<String, Room> roomsList;
    public RoomActions(){
        loadRooms(); // transfer all rooms from rooms.csv to the roomsList Hashmap
    }

    public void loadRooms(){
        LoadUp loader = new LoadUp(); // initialize gateway class for loading
        List<String> rooms = loader.getRooms(); // gateway method to transfer from rooms.csv to a list of room ids
        for (String id:rooms){
            Room room = new Room(id);
            roomsList.put(id, room);
        }
    }

    public void storeRooms(){
        Store store = new Store(); //initialize gateway class for storing
        ArrayList<String> rooms = new ArrayList<String>();
        for (String id:rooms){
            rooms.add(id);
        }
        store.storeRooms(rooms);
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
