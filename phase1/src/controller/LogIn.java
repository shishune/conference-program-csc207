package controller;

import entities.User;
import useCase.*;


public class LogIn {

    /**
     * This method is called when the user is logging in after they have inputted a username and password and checks if
     * the username and password are correct, if so returns the user object which was logging in, otherwise returns null
     * @param username A string the user inputs as their username
     * @param password Astring the user inputs as their password
     * @param userActions A UserAccountActions object so it can access the use case class
     * @return The user object of the user if the login was successful otherwise null if given username and password
     * are incorrct.
     */
    public String logIn(String username, String password, UserAccountActions userActions) {
        System.out.println("LOGGING IN");
        LoginActions l = new LoginActions();
        if (l.isLogin(username, password, userActions)) {
            return userActions.returnUsersHashMap().get(username).getId();
        }
        return "";
    }
}
