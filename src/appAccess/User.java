package appAccess;

import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private boolean login_status = false;

    Scanner sc = new Scanner(System.in);

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void login() {
        System.out.print("Username: ");
        // String username = sc.nextline();
        System.out.print("Password: ");
        // String password = sc.nextline();
    }
}
