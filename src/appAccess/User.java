package appAccess;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import fileIO.ReadFile;

public class User {
    private String username;
    private String password;
    private static HashMap<String, String> accounts;
    private boolean login_status = false;
    private static ReadFile accountsFile = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        try {
            User.getAccountsFromFile();
            this.login();
        } catch (IOException e) {
            System.err.println("Error when trying to get user account from file : " + e);
        } catch (NoSuchAlgorithmException f) {
            System.err.println("Error when trying to hashing the password : " + f);
        }
    }

    private static void getAccountsFromFile() throws IOException {
        User.accounts = new HashMap<>();

        User.accountsFile = new ReadFile("userAdmin.txt");
        for (int i = 0; i < User.accountsFile.getAmountOfData(); i++) {
            User.accounts.put(accountsFile.getRowData().get(i).get(0), accountsFile.getRowData().get(i).get(1));
        }
    }

    private String passwordHashing() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(this.password.getBytes());
        byte[] digestMessageInBytes = messageDigest.digest();

        String hashedPassword = new String();
        for (byte b : digestMessageInBytes) {
            hashedPassword += String.format("%02x", b);
        }

        return hashedPassword;
    }

    private void login() throws NoSuchAlgorithmException {
        if (User.accounts.containsKey(this.username)
                && User.accounts.get(this.username).equals(this.passwordHashing())) {
            this.login_status = true;
        }
    }

    public boolean getLoginStatus() {
        return this.login_status;
    }
}
