package OrderingSystem.Customer;

public interface ICustomersDataAccess {
    Customer getCustomerByEmail(String email);
    Customer getCustomerByPhoneNumber(String phoneNumber);
}
