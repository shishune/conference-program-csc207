package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class User{
    private String username;
    private boolean isLogin;
    private String password;
    private List<String> contactsList;
    String userID;


    public User (String username, String password, List<String> contactsList, boolean isLogin){
        this.username = username;
        this.isLogin = isLogin;
        this.password = password;
        this.contactsList = contactsList;
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


}