package gateway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

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
