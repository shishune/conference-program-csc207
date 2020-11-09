package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class User{
    protected String username;
    protected boolean isLogin;
    protected String password;
    protected List<String> contactsList;
    protected List<String> eventList;
    protected boolean isOrganizer;
    protected String userId;

    public User (String userId, String username, String password, List<String> contactsList, List<String> eventList,
                 boolean isLogin, boolean isOrganizer){

        this.username = username;
        this.isLogin = isLogin;
        this.password = password;
        this.contactsList = contactsList;
        this.eventList = eventList;
        this.userId = userId;
        this.isOrganizer = isOrganizer;
    }

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

    public abstract String getId();

    public String stringRepresentation() {
        String contacts = contactsList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "$");
        String events = eventList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "$");

        return userId + ", " + username + ", " + password + ", " + contacts + ", " + events + ", " + isLogin + ", " +isOrganizer + ", " ;
    }

}