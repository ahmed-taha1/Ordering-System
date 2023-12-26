package OrderingSystem.Checkout;

import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Shipping.Entities.IShippingCalculator;
import OrderingSystem.Shipping.Factories.ShippingCalculatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="checkout")
public class CheckoutController {
    private final ICustomersDataAccess customersDataAccess = OrderingSystemApplication.getCustomersDataAccess();
    private final IOrderDataAccess orderDataAccess = OrderingSystemApplication.getOrderDataAccess();
    private final ShippingCalculatorFactory shippingCalculatorFactory = ShippingCalculatorFactory.getInstance();
    @PostMapping("/")
    public ResponseEntity<String> checkout(@RequestBody String orderId){
        IOrderComponent orderComponent = orderDataAccess.getOrderById(Integer.parseInt(orderId));
        IShippingCalculator shippingCalculator = shippingCalculatorFactory.createShippingStrategy(orderComponent.getOrderType());
        try {
            CheckoutService.checkout(orderComponent,shippingCalculator,customersDataAccess);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.ok("Order Checkout successfully");
    }
}
