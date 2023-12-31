package OrderingSystem.Customer.DataAccess;

import OrderingSystem.Address.Address;
import OrderingSystem.Customer.Entities.Customer;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCustomersDataAccess implements ICustomersDataAccess {
    private static Map<String, Customer> customersDB;
    private static InMemoryCustomersDataAccess customersInMemoryDataAccessInstance = null;
    private InMemoryCustomersDataAccess(){
        customersDB = new HashMap<>();
        customersDB.put("ahmedtaha@yahoo.com", new Customer(
                    "ahmed taha",
                    "ahmedtaha@yahoo.com",
                    "password",
                    "01120293048",
                    120000000,
                    new Address("haram", "giza")));
        customersDB.put("ismailMagdy@gmail.com", new Customer(
                    "ismail magdy",
                    "ismailMagdy@gmail.com",
                    "password",
                    "01120293048",
                    400000,
                    new Address("haram", "giza")));
        customersDB.put("ahmedHany@gmail.com", new Customer(
                    "ahmed hany",
                    "ahmedHany@gmail.com",
                    "password",
                    "01120293048",
                    13000000,
                    new Address("haram", "giza")));
    }

    public static InMemoryCustomersDataAccess getInstance(){
        if (customersInMemoryDataAccessInstance == null){
            customersInMemoryDataAccessInstance = new InMemoryCustomersDataAccess();
        }
        return customersInMemoryDataAccessInstance;
    }

    @Override
    public Customer getCustomerByEmail(String email){
        return customersDB.get(email);
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber){
        for (Customer customer: customersDB.values()) {
            if(phoneNumber.equals(customer.getPhoneNumber())){
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean insertCustomerToDB(Customer customer) {
        customersDB.put(customer.getEmail(), customer);
        return true;
    }

}
