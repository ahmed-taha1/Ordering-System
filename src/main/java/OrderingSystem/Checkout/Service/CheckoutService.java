package OrderingSystem.Checkout.Service;

import OrderingSystem.Checkout.Entities.CheckoutDetails;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.DataAccess.ICustomersDataAccess;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.OrderStatus;
import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Orders.Service.OrdersService;
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
        Collection<SimpleOrder> orders = order.getOrders();
        Collection<CheckoutDetails>checkoutDetails = new ArrayList<>();
        for (SimpleOrder currentOrder : orders) {
            double shippingPrice = ShippingService.calculateShippingCost(currentOrder);
            double orderCost = currentOrder.getTotalCost();
            Customer customer = customersDataAccess.getCustomerByEmail(currentOrder.getMainOrderOwner());
            customer.withdraw(shippingPrice + orderCost);
            currentOrder.updateOrderStatus(OrderStatus.shipped);
            OrdersService.updateOrder(currentOrder);
            checkoutDetails.add(new CheckoutDetails(customer.getEmail(),orderCost,shippingPrice,currentOrder.getOrderStatus()));
       }
       return checkoutDetails;
    }
}
