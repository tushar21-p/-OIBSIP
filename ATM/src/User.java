import java.util.*;
public class User {
    private String userId;
    private String userPin;
    private Account account;

    public User(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.account = new Account(userId, userPin, initialBalance);
    }

    public String getUserId() {
        return userId;
    }

    public boolean verifyPin(String pin) {
        return userPin.equals(pin);
    }

    public Account getAccount() {
        return account;
    }
}
