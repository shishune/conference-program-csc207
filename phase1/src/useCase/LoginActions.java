package useCase;

import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginActions {
    //TODO: Changes boolean, sees if password given matched password stored and username as well?? how pls

    public boolean isLogin(String username, String password){
        String filename = "entityIds.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = null;

            if (filename.isEmpty()) {
                 return false;
            }

            while ((line = br.readLine()) != null) {
                if (line.contains(username)){
                    if (line.contains(password)){

                        return true;
                    }
                }
            }

        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

}
