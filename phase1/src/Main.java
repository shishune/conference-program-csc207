import entities.*;
import controller.AccountController;

public class Main {
    public static void main(String[] args) {
        // initialize Login/Signup Controller as lsc
        // lsc.run()
        AccountController account = new AccountController();
        account.run();
        // Example: See week 5 slides and code 'ReadWriteEx.zip' file.
    }
}
