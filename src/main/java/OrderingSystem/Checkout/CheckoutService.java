package OrderingSystem.Checkout;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.OrderStatus;
import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Shipping.Entities.IShippingCalculator;

import java.util.ArrayList;
import java.util.Collection;

public class CheckoutService {
    public static Collection<CheckoutDetails> checkout(IOrderComponent order, IShippingCalculator shippingCalculator, ICustomersDataAccess customersDataAccess) throws Exception {
       Collection<SimpleOrder> orders = order.getOrderDetails();
       Collection<CheckoutDetails>checkoutDetails = new ArrayList<>();
       for (SimpleOrder currentOrder : orders) {
           double shippingPrice = shippingCalculator.calculateShippingCost(currentOrder);
           double orderCost = currentOrder.getTotalPrice();
           Customer customer = customersDataAccess.getCustomerByEmail(currentOrder.getMainOrderOwner());
           customer.withdraw(shippingPrice + orderCost);
           currentOrder.updateOrderStatus(OrderStatus.shipped);
           checkoutDetails.add(new CheckoutDetails(customer.getEmail(),orderCost,shippingPrice,currentOrder.getOrderStatus()));
       }
       return checkoutDetails;
    }
}
