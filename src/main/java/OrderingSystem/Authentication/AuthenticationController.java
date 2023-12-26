package OrderingSystem.Authentication;

import OrderingSystem.OrderingSystemApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "")
public class AuthenticationController {
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody RequestsBodyRecords.LoginCustomerDataBodyRequest request){
        if(!AuthenticationService.login(request)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid user name or password");
        }
        return ResponseEntity.ok("Successfully logged in");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RequestsBodyRecords.RegisterCustomerDataBodyRequest request){
        if(!AuthenticationService.register(request)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
        }
        return ResponseEntity.ok("Successfully registered");
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(){
        if(OrderingSystemApplication.getActiveUser() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login first");
        }
        OrderingSystemApplication.setActiveUser(null);
        return ResponseEntity.ok("logged out Successfully");
    }

    // for testing only
    @GetMapping("getActiveUserEmail")
    public String getEmail(){
        if(OrderingSystemApplication.getActiveUser() == null){
            return "there is no active user";
        }
        return OrderingSystemApplication.getActiveUser().getEmail();
    }

}