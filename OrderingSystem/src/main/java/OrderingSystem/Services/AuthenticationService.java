package OrderingSystem.Services;

import OrderingSystem.Address.Address;
import OrderingSystem.Authentication.AuthenticationController;
import OrderingSystem.Customer.InMemoryCustomersDataAccess;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.OrderingSystemApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "OrderingSystem")
public class AuthenticationService {
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginCustomerDataBodyRequest request){
        if(!AuthenticationController.login(request)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid user name or password");
        }
        return ResponseEntity.ok("Successfully logged in");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterCustomerDataBodyRequest request){
        if(!AuthenticationController.register(request)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
        }
        return ResponseEntity.ok("Successfully registered");
    }


    // for testing only
    @GetMapping("getActiveUserEmail")
    public String getEmail(){
        if(OrderingSystemApplication.activeUser == null){
            return "there is no active user";
        }
        return OrderingSystemApplication.activeUser.getEmail();
    }

    public record LoginCustomerDataBodyRequest(
        String email,
        String password
    ){}
    public record RegisterCustomerDataBodyRequest(
            String email,
            String password,
            String phoneNumber,
            Address address,
            double balance
    ){}
}
