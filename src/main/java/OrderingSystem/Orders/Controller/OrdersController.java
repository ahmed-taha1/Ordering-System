package OrderingSystem.Orders.Controller;

import OrderingSystem.Address.Address;
import OrderingSystem.Customer.Customer;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.OrderItem;
import OrderingSystem.Orders.Factories.OrdersFactory;
import OrderingSystem.Orders.PlaceOrdersService;
import OrderingSystem.Products.IProductsDataAccess;
import OrderingSystem.Products.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "orders")
public class OrdersController {
    private final IOrderDataAccess orderDataAccess = OrderingSystemApplication.getOrderDataAccess();
    private final IProductsDataAccess productsDataAccess = OrderingSystemApplication.getProductsDataAccess();
    private final OrdersFactory ordersFactory = OrderingSystemApplication.getOrdersFactory();
    @PostMapping(path = "placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody RequestBodyRecords.PlaceOrderBodyRequest request){
        Customer activeUser = OrderingSystemApplication.getActiveUser();
        if(activeUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","must be logged in to place orders"));
        }
        int placedOrderID = PlaceOrdersService.placeOrder(request, activeUser.getEmail(), ordersFactory, productsDataAccess, orderDataAccess);
        return ResponseEntity.ok(new ResponseBodyRecords.PlaceOrderBodyResponse(placedOrderID, "Order created Successfully"));
    }
    @GetMapping("/")
    public ResponseEntity<Object> getOrderDetails(@RequestBody RequestBodyRecords.GetOrderDetailsBodyRequest request){
        IOrderComponent orderComponent = orderDataAccess.getOrderById(request.id());
        if(orderComponent == null){
            System.out.println("NOT FOUND");
            return null;
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyRecords.getOrderDetailsResponse(null,"Order not found"));
        }
//        Map<String,Object>res = new HashMap<>();
//        res.put("id",orderComponent.getId());
//        res.put("type",orderComponent.getOrderType());
//        res.put("ownerEmail",orderComponent.getMainOrderOwner());
//        res.put("address",new Address("s","s"));
        List<OrderItem> orderItems=  new ArrayList<>();
        orderItems.add(new OrderItem(new Product("q","q","a","a",1022),10));
        orderItems.add(new OrderItem(new Product("q","q","a","a",1022),10));
        orderItems.add(new OrderItem(new Product("q","q","a","a",1022),10));
//        res.put("products",orderItems);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyRecords.getOrderDetailsResponse(orderItems , orderComponent.getMainOrderOwner(), orderComponent.getOrderStatus(), new Address("2asd", "fsd"), orderComponent.getId()));
    }
}