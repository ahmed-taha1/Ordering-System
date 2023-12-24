package OrderingSystem.Customer;

public class Customer {
    private final String email;
    private final String password;
    private final String phoneNumber;
    private final double balance;
    public Customer(String email, String password, String phoneNumber, double balance) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public double getBalance() {
        return balance;
    }
}
