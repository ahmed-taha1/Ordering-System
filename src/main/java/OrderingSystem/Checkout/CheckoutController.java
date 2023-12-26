package OrderingSystem.Checkout;

import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Shipping.Entities.IShippingCalculator;
import OrderingSystem.Shipping.Factories.ShippingCalculatorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="checkout")
public class CheckoutController {
    private final ICustomersDataAccess customersDataAccess = OrderingSystemApplication.getCustomersDataAccess();
    private final IOrderDataAccess orderDataAccess = OrderingSystemApplication.getOrderDataAccess();
    private final ShippingCalculatorFactory shippingCalculatorFactory = ShippingCalculatorFactory.getInstance();
    @PostMapping("/")
    public ResponseEntity<Object> checkout(@RequestBody RequestsBodyRecords.CheckoutRequestBody requestBody){
        IOrderComponent orderComponent = orderDataAccess.getOrderById(requestBody.orderId());
        if(orderComponent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Order with Id "+requestBody.orderId() +" Not found"));
        }
        IShippingCalculator shippingCalculator = shippingCalculatorFactory.createShippingStrategy(orderComponent.getOrderType());
        try {
            CheckoutService.checkout(orderComponent,shippingCalculator,customersDataAccess);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",exception.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Order Checkout successfully"));
    }
}
