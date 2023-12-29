package OrderingSystem.Customer.Service;

import OrderingSystem.Customer.Controller.ResponseBodyRecords;
import OrderingSystem.Customer.DataAccess.ICustomersDataAccess;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.OrderingSystemApplication;

public class CustomerService {
    private static ICustomersDataAccess customersDataAccess = OrderingSystemApplication.getCustomersDataAccess();
    public static Customer getCustomerByEmail(String email){
        return customersDataAccess.getCustomerByEmail(email);
    }
}
