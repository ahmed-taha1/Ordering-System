package OrderingSystem.Customer.Controller;

import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.Service.CustomerService;
import OrderingSystem.OrderingSystemApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "customer")
public class CustomerController {
    @GetMapping(path = "profile")
    public Map<String, Object> getProfile(){
        final Map<String, Object> response = new HashMap<>();
        Customer loggedUser = OrderingSystemApplication.getActiveUser();
        if(loggedUser == null){
            response.put("profile", null);
            response.put("message", "please login first");
            return response;
        }
        String email = loggedUser.getEmail();
        Customer customer = CustomerService.getCustomerByEmail(email);
        if(customer == null){
            response.put("profile", null);
            response.put("message", "please login first");
        }
        else {
            response.put("profile", new ResponseBodyRecords.getProfileResponse(customer.getEmail(),customer.getAddress(), customer.getBalance(), customer.getName()));
        }
        return response;
    }
}