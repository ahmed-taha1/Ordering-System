package OrderingSystem.Authentication.Controller;

import OrderingSystem.Address.Address;

public class RequestsBodyRecords {
    public record LoginCustomerDataBodyRequest(
            String email,
            String password
    ){}
    public record RegisterCustomerDataBodyRequest(
            String email,
            String password,
            String phoneNumber,
            Address address,
            double balance,
            String name
    ){}
}
