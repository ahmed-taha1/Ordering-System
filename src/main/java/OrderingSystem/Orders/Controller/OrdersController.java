package OrderingSystem.Orders.Controller;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "orders")
public class OrdersController {
    @PostMapping(path = "placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody RequestBodyRecords.PlaceOrderBodyRequest request){
        Customer activeUser = OrderingSystemApplication.getActiveUser();
        if(activeUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","must be logged in to place orders"));
        }
        try {
            int placedOrderID = OrdersService.placeOrder(request, activeUser.getEmail());
            return ResponseEntity.ok(new ResponseBodyRecords.PlaceOrderBodyResponse(placedOrderID, "Order created Successfully"));
        } catch (Exception exception){
            if(exception instanceof CustomException){
                return ResponseEntity.status(((CustomException) exception).getStatusCode()).body(new ResponseBodyRecords.placeOrderFailedRecord(exception.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBodyRecords.placeOrderFailedRecord(exception.getMessage()));
        }
    }
    @GetMapping("/details")
    public ResponseEntity<Object> getOrderDetails(@RequestBody RequestBodyRecords.GetOrderDetailsBodyRequest request){
        IOrderComponent orderComponent = OrdersService.findOrder(request.id());
        if(orderComponent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyRecords.getOrderDetailsResponse(null,null, 0.0,"Order not found"));
        }
        if(!OrderingSystemApplication.getActiveUser().getEmail().equals(orderComponent.getMainOrderOwner())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseBodyRecords.getOrderDetailsResponse(null, null, 0.0,"this ordersDetails doesn't belong to you"));
        }
        return ResponseEntity.ok(new ResponseBodyRecords.getOrderDetailsResponse(orderComponent.getMainOrderOwner(), orderComponent.getOrderDetails(), orderComponent.getTotalCost(),"ordersDetails found"));
    }
}