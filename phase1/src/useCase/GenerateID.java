package useCase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerateID {

    protected int objectId; // objectId should be made from an user class/gateway
    //TODO: ask ta if it should be static. If it's not static, how can we call method from other classes?
    //TODO: load up should proved the number of IDs

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
            // TODO: tell user error was generated
            e.printStackTrace();
        }
        objectId++;

        return String.format("%16d", objectId);
    }
}
