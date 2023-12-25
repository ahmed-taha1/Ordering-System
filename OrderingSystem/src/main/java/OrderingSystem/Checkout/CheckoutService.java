package OrderingSystem.Checkout;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Shipping.Entities.IShippingCalculator;

import java.util.Collection;

public class CheckoutService {
    public void checkout(IOrderComponent order, IShippingCalculator shippingCalculator, ICustomersDataAccess customersDataAccess) throws Exception {
       Collection<SimpleOrder> orders = order.getOrderDetail();
       for (SimpleOrder currentOrder : orders) {
           double shippingPrice = shippingCalculator.calculateShippingCost(currentOrder);
           double orderCost = currentOrder.getTotalPrice();
           Customer customer = customersDataAccess.getCustomerByEmail(currentOrder.getMainOrderOwner());
           customer.withdraw(shippingPrice + orderCost);
       }
    }
}
