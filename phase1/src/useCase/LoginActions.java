package useCase;

import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginActions {

    public boolean isLogin(String username, String password) {

        UserAccountActions u = new UserAccountActions();
        User user = u.usersHashMap.get(username);
         if (user.getPassword().equals(password)) {
             user.setLogin(true);
             return true;
         }
        user.setLogin(false);
        return false;
    }
}