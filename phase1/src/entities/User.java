package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class User{
    private String username;
    private boolean isLogin;
    private String password;
    private List<String> talkList;
    private List<String> contactsList;
    String userID; // TODO: Make sure it implements java.io (DUE NOV 03)


    public User (String username, String password, List<String> talkList, List<String> contactsList, boolean isLogin){
        this.username = username;
        this.isLogin = isLogin;
        this.password = password;
        this.contactsList = contactsList;
        this.talkList = talkList;
    }

    public abstract String generateUserID();

    public boolean getIsLogin(){
        return isLogin;
    }

    public List<String> getTalkList(){
        return talkList;
    }
    public List<String> getContactsList(){
        return contactsList;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}