package OrderingSystem.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomersInMemoryDataAccess implements ICustomersDataAccess {
    private Map<String, Customer> customersDB;
    private static CustomersInMemoryDataAccess customersInMemoryDataAccessInstance = null;
    private CustomersInMemoryDataAccess(){
        customersDB = new HashMap<>();
        customersDB.put("ahmedtaha@yahoo.com", new Customer("ahmedtaha@yahoo.com", "password", "01120293048", 1200));
    }

    public static CustomersInMemoryDataAccess getInstance(){
        if (customersInMemoryDataAccessInstance == null){
            customersInMemoryDataAccessInstance = new CustomersInMemoryDataAccess();
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
}
