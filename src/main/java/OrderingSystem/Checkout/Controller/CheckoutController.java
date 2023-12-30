package OrderingSystem.Checkout.Controller;

import OrderingSystem.Checkout.Entities.CheckoutDetails;
import OrderingSystem.Checkout.Service.CheckoutService;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.OrderingSystemApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path="checkout")
public class CheckoutController {
    @PostMapping("/")
    public ResponseEntity<Object> checkout(@RequestBody RequestsBodyRecords.CheckoutRequestBody request){
        Customer activeUser = OrderingSystemApplication.getActiveUser();
        if(activeUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "please login first"));
        }
        try {
            Collection<CheckoutDetails> checkoutDetails = CheckoutService.checkout(request, activeUser.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyRecords.CheckoutDetailsRecord(checkoutDetails,"Checked out successfully"));
        }catch (Exception exception){
            if(exception instanceof CustomException){
                return ResponseEntity.status(((CustomException) exception).getStatusCode()).body(new ResponseBodyRecords.CheckoutFailedRecord(exception.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBodyRecords.CheckoutFailedRecord(exception.getMessage()));
        }
    }
}