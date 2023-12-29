package OrderingSystem.Checkout;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.OrderStatus;
import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Orders.OrdersService.OrdersService;
import OrderingSystem.Shipping.Entities.IShippingCalculator;
import OrderingSystem.Shipping.Factories.ShippingCalculatorFactory;
import OrderingSystem.Shipping.Service.ShippingService;
import OrderingSystem.StatusCodes.StatusCodes;

import java.util.ArrayList;
import java.util.Collection;

public class CheckoutService {
    private static final ICustomersDataAccess customersDataAccess = OrderingSystemApplication.getCustomersDataAccess();
    public static Collection<CheckoutDetails> checkout(int orderId) throws Exception {
        IOrderComponent order = OrdersService.findOrder(orderId);
        if(order == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"Order Not found");
        }
        Collection<SimpleOrder> orders = order.getOrderDetails();
        Collection<CheckoutDetails>checkoutDetails = new ArrayList<>();
        for (SimpleOrder currentOrder : orders) {
            double shippingPrice = ShippingService.calculateShippingCost(currentOrder);
            double orderCost = currentOrder.getTotalPrice();
            Customer customer = customersDataAccess.getCustomerByEmail(currentOrder.getMainOrderOwner());
            customer.withdraw(shippingPrice + orderCost);
            currentOrder.updateOrderStatus(OrderStatus.shipped);
            checkoutDetails.add(new CheckoutDetails(customer.getEmail(),orderCost,shippingPrice,currentOrder.getOrderStatus()));
       }
       return checkoutDetails;
    }
}
