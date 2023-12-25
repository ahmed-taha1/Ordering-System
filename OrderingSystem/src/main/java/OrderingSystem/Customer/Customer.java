package OrderingSystem.Customer;

import OrderingSystem.Address.Address;

public class Customer {
    private final String email;
    private final String password;
    private final String phoneNumber;
    private double balance;
    private final Address address;
    public Customer(String email, String password, String phoneNumber, double balance, Address address) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.address = address;
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
    public Address getAddress(){
        return address;
    }
    public void withdraw(double amount) throws Exception {
        if(amount > balance){
            throw new Exception("Insufficient Balance");
        }
        this.balance -= amount;
    }
}
