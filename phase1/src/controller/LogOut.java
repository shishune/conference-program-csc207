package controller;

import gateway.LoadUp;
import gateway.LoadUpIGateway;
import presenter.AccountPresenter;
import useCase.*;
import gateway.Store;

public class LogOut {
    public void loggingOut() {

        Store store = new Store();
        UserAccountActions u = new UserAccountActions();
        // login procedure...
    }

    public void exit(){
        System.exit(1);
    }
}
