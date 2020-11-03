package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class User{
    protected String username;
    protected boolean isLogin;
    protected String password;
    protected List<String> contactsList;
    protected List<String> eventList;
    protected String userID;
    protected int numUsers;


    public User (String username, String password, List<String> contactsList, List<String> eventList, boolean isLogin){
        this.username = username;
        this.isLogin = isLogin;
        this.password = password;
        this.contactsList = contactsList;
        this.eventList = eventList;
        //this.userID = "U" + numUsers;
        //numUsers ++;
        //questions: how to store id? should we use files?
    }

    public abstract String generateId();

    public boolean getIsLogin(){
        return isLogin;
    }

    public List<String> getContactsList(){
        return contactsList;
    }
    public void setContactsList(List<String> contactsList){
        this.contactsList = contactsList;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getEventList(){
        return eventList;
    }

    public void setEventList(List<String> eventList){
        this.eventList = eventList;
    }


}