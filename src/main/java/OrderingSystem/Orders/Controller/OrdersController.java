package OrderingSystem.Orders.Controller;
import OrderingSystem.Customer.Customer;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.OrdersService.OrdersService;
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
        int placedOrderID = OrdersService.placeOrder(request, activeUser.getEmail());
        return ResponseEntity.ok(new ResponseBodyRecords.PlaceOrderBodyResponse(placedOrderID, "Order created Successfully"));
    }
    @GetMapping("/")
    public ResponseEntity<ResponseBodyRecords.getOrderDetailsResponse> getOrderDetails(@RequestBody RequestBodyRecords.GetOrderDetailsBodyRequest request){
        IOrderComponent orderComponent = OrdersService.findOrder(request.id());
        if(orderComponent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyRecords.getOrderDetailsResponse(null,"Order not found"));
        }
        // if he is not the owner of the order
        if(!orderComponent.getMainOrderOwner().equals(OrderingSystemApplication.getActiveUser().getEmail())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseBodyRecords.getOrderDetailsResponse(null, "this order doesn't belong to you"));
        }
        return ResponseEntity.ok(new ResponseBodyRecords.getOrderDetailsResponse(orderComponent, "order found"));
    }
}