package OrderingSystem.Checkout.Controller;

import OrderingSystem.Checkout.Entities.CheckoutDetails;
import OrderingSystem.Checkout.Service.CheckoutService;
import OrderingSystem.Exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path="checkout")
public class CheckoutController {
    @PostMapping("/")
    public ResponseEntity<Object> checkout(@RequestBody RequestsBodyRecords.CheckoutRequestBody requestBody){
        try {
            Collection<CheckoutDetails> checkoutDetails = CheckoutService.checkout(requestBody.orderId());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyRecords.CheckoutDetailsRecord(checkoutDetails,"Checked out successfully"));
        }catch (Exception exception){
            if(exception instanceof CustomException){
                return ResponseEntity.status(((CustomException) exception).getStatusCode()).body(new ResponseBodyRecords.CheckoutFailedRecord(exception.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBodyRecords.CheckoutFailedRecord(exception.getMessage()));
        }
    }
}