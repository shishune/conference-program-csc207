package useCase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerateID {
    //TODO: Use the usernums way so it increases everytime generateID is called
    //TODO: implement java.io :')

    protected int objectId; // objectId should be made from an user class/gateway


    public String generateId() {
        String filename = "entityIds.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = null;

            if (filename.isEmpty()) {
                objectId = -1;
            }

            while ((line = br.readLine()) != null) {
                objectId++;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        objectId++;

        return String.format("%16d", objectId);
    }
}
