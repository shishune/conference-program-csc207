package gateway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import entities.Message;

public class Store implements StoreIGateway{
    //idk, just an idea
    @Override
    public void storeRooms(ArrayList<String> rooms) {
        try {
            FileWriter csvWriter = new FileWriter("rooms.csv");

            for (String id : rooms) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

    public void storeMessages(HashMap<String, String> messages) {
        String path = "phase1/src/assets/dataFiles/messages.csv";
        try {
            ArrayList<HashMap<String, String>> messageArray = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            map = new HashMap<String, String>();
            map.put(Message.getMessageId(), Message.getMessage());
            messageArray.add(map);

            FileWriter writer;
            writer = new FileWriter(path, true);
            for (HashMap<String, String> stringStringHashMap : messageArray) {
                writer.write(stringStringHashMap.get(Message.getMessageId()).toString());
                writer.write(",");
                writer.write(stringStringHashMap.get(Message.getMessage()).toString());
                writer.write("\r\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
