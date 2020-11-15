package useCase;

import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A use case class which is responsible for validating of the user's username and password match
 * @author Mizna & Jiessie
 * @version 1
 * */

public class LoginActions {

    /**
     * Returns true when the username and password are correct otherwise returns false.
     * @param username the username representing the username
     * @param password the string representing the password
     * @return A boolean if the username and password given are correct
     * */
    public boolean isLogin(String username, String password, UserAccountActions userAccountActions) {
        if (userAccountActions.returnUsersHashMap().containsKey(username)){
            User user = userAccountActions.returnUsersHashMap().get(username);
             if (user.getPassword().equals(password)) {
                 user.setLogin(true);
                 return true;
             }
            user.setLogin(false);
            return false;
        }
        return false;
    }
}