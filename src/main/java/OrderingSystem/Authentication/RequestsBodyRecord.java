package OrderingSystem.Authentication;

import OrderingSystem.Address.Address;

public class RequestsBodyRecord {
    public static record LoginCustomerDataBodyRequest(
            String email,
            String password
    ){}
    public static record RegisterCustomerDataBodyRequest(
            String email,
            String password,
            String phoneNumber,
            Address address,
            double balance
    ){}
}
