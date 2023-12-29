package OrderingSystem.Orders.Service;


import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.Service.CustomerService;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.Notification.Entities.INotification;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.NotificationSchedulerService;
import OrderingSystem.Notification.Templates;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Controller.RequestBodyRecords;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.Entities.*;
import OrderingSystem.Orders.Factories.OrdersFactory;
import OrderingSystem.Products.Entities.Product;
import OrderingSystem.Products.Service.ProductsService;
import OrderingSystem.StatusCodes.StatusCodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdersService {
    private static final IOrderDataAccess orderDataAccess = OrderingSystemApplication.getOrderDataAccess();
    private static final OrdersFactory ordersFactory = OrderingSystemApplication.getOrdersFactory();
    private static final NotificationType notificationType = OrderingSystemApplication.getNotificationType();
    public static int placeOrder(RequestBodyRecords.PlaceOrderBodyRequest orderBodyRequest, String mainOwnerEmail)throws CustomException{
        OrderType orderType = orderBodyRequest.orderType();
        Collection<RequestBodyRecords.OrderDetailsRecord> orderDetailsRecord = orderBodyRequest.orders();
        List<SimpleOrder> orders = new ArrayList<>();
        for (RequestBodyRecords.OrderDetailsRecord orderDetails : orderDetailsRecord) {
            Collection<RequestBodyRecords.OrderItemRecord>orderItemRecords = orderDetails.products();
            List<OrderItem>items = new ArrayList<>();
            for(RequestBodyRecords.OrderItemRecord orderItems :orderItemRecords){
                Product product = ProductsService.findProductBySerialNumber(orderItems.serialNumber());
                items.add(new OrderItem(product, orderItems.count()));
            }
            SimpleOrder order = new SimpleOrder(orderDetails.orderOwnerEmail(),orderDetails.address(),items);
            orders.add(order);
            Customer customer = CustomerService.getCustomerByEmail(orderDetails.orderOwnerEmail());
            if(customer == null){
                throw new CustomException(StatusCodes.BAD_REQUEST,"User with email "+orderDetails.orderOwnerEmail()+ " doesn't not exist");
            }
            String message = Templates.createPlaceOrderMessage(customer.getName(),order.getProductsNames());
            NotificationSchedulerService.scheduleNotification(customer.getEmail(), notificationType, message);
        }
        IOrderComponent order = ordersFactory.createOrder(orderType,mainOwnerEmail,orders);
        orderDataAccess.insertOrder(order);
        return order.getId();
    }
    public static IOrderComponent findOrder(int orderId){
        return orderDataAccess.getOrderById(orderId);
    }

    public static void updateOrder(IOrderComponent order){
        if(findOrder(order.getId()) != null){
            orderDataAccess.updateOrder(order);
        }
    }
}