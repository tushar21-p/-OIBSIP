import java.util.*;
public class ATM {
    private User currentUser;
    private List<User> users;

    public ATM() {
        this.users = new ArrayList<>();
        // Add some users for testing
        users.add(new User("user1", "1234", 1000));
        users.add(new User("user2", "5678", 500));
        users.add(new User("user3", "9876", 200));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter User PIN: ");
            String userPin = scanner.nextLine();

            boolean userAuthenticated = authenticateUser(userId, userPin);
            if (userAuthenticated) {
                System.out.println("Authentication successful!");
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (choice) {
                    case 1:
                        displayTransactionHistory();
                        break;
                    case 2:
                        performWithdrawal();
                        break;
                    case 3:
                        performDeposit();
                        break;
                    case 4:
                        performTransfer();
                        break;
                    case 5:
                        checkBalance();
                        break;
                    case 6:
                        quit();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Authentication failed. Please try again.");
            }

        }
    }
    private boolean authenticateUser(String userId, String userPin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.verifyPin(userPin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    private void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. Quit");
        System.out.print("Enter your choice: ");
    }
    private void displayTransactionHistory() {
        Account account = currentUser.getAccount();
        account.printTransactionHistory();
    }
    private void performWithdrawal() {
        System.out.print("Enter the amount to withdraw: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        Account account = currentUser.getAccount();
        account.withdraw(amount);
    }
    private void performDeposit() {
        System.out.print("Enter the amount to deposit: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        Account account = currentUser.getAccount();
        account.deposit(amount);
    }
    private void performTransfer() {
        System.out.print("Enter the recipient's User ID: ");
        Scanner scanner = new Scanner(System.in);
        String recipientUserId = scanner.nextLine();

        User recipient = getUserById(recipientUserId);
        if (recipient != null) {
            System.out.print("Enter the amount to transfer: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume the newline character

            Account senderAccount = currentUser.getAccount();
            Account recipientAccount = recipient.getAccount();

            senderAccount.transfer(recipientAccount, amount);
        } else {
            System.out.println("Recipient User ID not found.");
        }
    }
    public void checkBalance() {
        Account account = currentUser.getAccount();
        double balance = account.getBalance();
        System.out.println("Current balance: $" + balance);
    }
    private User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    private void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }
}