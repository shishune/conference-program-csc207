package useCase;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActions {

    public boolean addUserContactList(User toMe, User addMe) {
        boolean isUsername = toMe.getContactsList().contains(addMe.getUsername());
        if (isUsername) {
            return false;
        }
        else {

            List<String> toMeContacts = toMe.getContactsList();
            toMeContacts.add(addMe.getUsername());
            toMe.setContactsList(toMeContacts);
            return true;
        }}































































































}
