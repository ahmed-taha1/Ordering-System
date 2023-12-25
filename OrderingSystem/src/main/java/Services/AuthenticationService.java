package Services;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.CustomersInMemoryDataAccess;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.OrderingSystemApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "OrderingSystem")
public class AuthenticationService {
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody userDataBodyRequest request){
        ICustomersDataAccess customersDataAccess = CustomersInMemoryDataAccess.getInstance();
        Customer quiredCustomer = customersDataAccess.getCustomerByEmail(request.email);
        if(quiredCustomer == null || !quiredCustomer.getPassword().equals(request.password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid user name or password");
        }
        OrderingSystemApplication.activeUser = quiredCustomer;
        return ResponseEntity.ok("Authenticated Successfully");
    }

    // for testing only
    @GetMapping("GetActiveUserEmail")
    public String getEmail(){
        if(OrderingSystemApplication.activeUser == null){
            return "there is no active user";
        }
        return OrderingSystemApplication.activeUser.getEmail();
    }
    record userDataBodyRequest(
        String email,
        String password
    ){}
}
