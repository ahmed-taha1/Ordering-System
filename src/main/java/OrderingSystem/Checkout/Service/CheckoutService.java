package OrderingSystem.Checkout.Service;

import OrderingSystem.Checkout.Controller.RequestsBodyRecords;
import OrderingSystem.Checkout.Entities.CheckoutDetails;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.DataAccess.ICustomersDataAccess;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.NotificationSchedulerService;
import OrderingSystem.Notification.Templates;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Controller.RequestBodyRecords;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.OrderStatus;
import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Orders.Service.OrdersService;
import OrderingSystem.Shipping.Service.ShippingService;
import OrderingSystem.StatusCodes.StatusCodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CheckoutService {
    private static final ICustomersDataAccess customersDataAccess = OrderingSystemApplication.getCustomersDataAccess();
    private static final NotificationType notificationType = OrderingSystemApplication.getNotificationType();
    private static final NotificationSchedulerService notificationSchedulerService = OrderingSystemApplication.getNotificationSchedulerService();

    public static Collection<CheckoutDetails> checkout(RequestsBodyRecords.CheckoutRequestBody request, String loggedInUserEmail) throws Exception{
        IOrderComponent order = OrdersService.findOrder(request.orderId());
        if(order == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"Order Not found");
        }
        if(!Objects.equals(order.getMainOrderOwner(), loggedInUserEmail)){
            throw new CustomException(StatusCodes.UN_AUTHORIZED,"Cannot Pay for an Order that doesn't belong to you");
        }
        if(order.getOrderStatus() != OrderStatus.preparing){
            throw new CustomException(StatusCodes.FORBIDDEN,"This order is Already Payed for and "+order.getOrderStatus());
        }
        Collection<SimpleOrder> orders = order.getOrders();
        Collection<CheckoutDetails>checkoutDetails = new ArrayList<>();
        for (SimpleOrder currentOrder : orders) {
            double shippingPrice = ShippingService.calculateShippingCost(currentOrder);
            double orderCost = currentOrder.getTotalCost();
            Customer customer = customersDataAccess.getCustomerByEmail(currentOrder.getMainOrderOwner());
            customer.withdraw(shippingPrice + orderCost);
            currentOrder.updateOrderStatus(OrderStatus.shipped);
            checkoutDetails.add(new CheckoutDetails(customer.getName(),customer.getEmail(),orderCost,shippingPrice,currentOrder.getOrderStatus()));
       }
        order.updateOrderStatus(OrderStatus.shipped);
        OrdersService.updateOrder(order);
        for(CheckoutDetails details:checkoutDetails){
            String message = Templates.createCheckoutMessage(details.getOwnerName(),details.getOrderPrice(),details.getShippingCost());
            notificationSchedulerService.scheduleNotification(loggedInUserEmail, notificationType, message);
        }
       return checkoutDetails;
    }
}