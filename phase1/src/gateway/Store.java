package gateway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import useCase.MessageActions;
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

    public void storeMessages(MessageActions messageActions) {
        ArrayList<String> messagesList = new ArrayList<String>();
        String path = "../assets/dataFiles/messages.csv";
        messagesList = messageActions.storeMessages();
        try {
            FileWriter writer;
            writer = new FileWriter(path, false);
            for (String message : messagesList){
                writer.write(message);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void storeOrganizers(ArrayList<String> organizer) {
        try {
            FileWriter csvWriter = new FileWriter("organizers.csv");

            for (String id : organizer) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void storeAttendees(ArrayList<String> attendee) {
        try {
            FileWriter csvWriter = new FileWriter("attendees.csv");

            for (String id : attendee) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void storeEntities(ArrayList<String> attendee, ArrayList<String> organizer, ArrayList<String> message, ArrayList<String> room, ArrayList<String> event, ArrayList<String> speaker) {
        try {
            FileWriter csvWriter = new FileWriter("entities.csv");

            for (String id : attendee) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : organizer) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : message) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : room) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : event) {
                csvWriter.append(id);
                csvWriter.append("\n");
            }
            for (String id : speaker) {
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
