package OrderingSystem.Customer.Entities;

import OrderingSystem.Address.Address;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.StatusCodes.StatusCodes;

public class Customer {
    private final String email;
    private final String password;
    private final String phoneNumber;
    private double balance;
    private final Address address;
    private final String name;
    public Customer(String name,String email, String password, String phoneNumber, double balance, Address address) {
        this.name = name;
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
    public String getName(){return name;}
    public void withdraw(double amount) throws Exception {
        if(amount > balance){
            throw new CustomException(StatusCodes.FORBIDDEN,"Insufficient Balance");
        }
        this.balance -= amount;
    }
}
