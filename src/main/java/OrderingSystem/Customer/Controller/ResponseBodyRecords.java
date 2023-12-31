package OrderingSystem.Customer.Controller;

import OrderingSystem.Address.Address;

public class ResponseBodyRecords {
    public record getProfileResponse(
            String email,
            Address address,
            String balance,
            String name
    ){}
}
