package appAccess;

import java.security.NoSuchAlgorithmException;

public class Login {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        User firstAccount = new User("yellowFelix", "sapiNgamuk1234");
        User seccondAccount = new User("ayamGaming", "sapiTerbang3215");
        System.out.println("firstAccount > login status = " + firstAccount.getLoginStatus());
        System.out.println("seccondAccount > login status = " + seccondAccount.getLoginStatus());
    }
}
