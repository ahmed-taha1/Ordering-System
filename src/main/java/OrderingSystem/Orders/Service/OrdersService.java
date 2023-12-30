package OrderingSystem.Orders.Service;


import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.Service.CustomerService;
import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.Entities.TemplateMessage;
import OrderingSystem.Notification.Service.NotificationSchedulerService;
import OrderingSystem.Notification.factories.TemplatesFactory;
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

public class
OrdersService {
    private static final IOrderDataAccess orderDataAccess = OrderingSystemApplication.getOrderDataAccess();
    private static final OrdersFactory ordersFactory = OrderingSystemApplication.getOrdersFactory();
    private static final NotificationType notificationType = OrderingSystemApplication.getNotificationType();
    private static final NotificationSchedulerService notificationSchedulerService = OrderingSystemApplication.getNotificationSchedulerService();

    public static int placeOrder(RequestBodyRecords.PlaceOrderBodyRequest orderBodyRequest, String loggedInUserEmail)throws CustomException{
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
            notificationSchedulerService.scheduleNotification(loggedInUserEmail, notificationType, TemplatesFactory.createPlaceOrderMessage(customer.getName(),order.getProductsNames()));
        }
        IOrderComponent order = ordersFactory.createOrder(orderType,loggedInUserEmail,orders);
        orderDataAccess.insertOrder(order);
        return order.getId();
    }
    public static void cancelOrder(RequestBodyRecords.CancelOrderBodyRequest orderBodyRequest,String loggedInUserEmail)throws CustomException{
        IOrderComponent order = findOrder(orderBodyRequest.id());
        if(order == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"This Order doesn't Exist");
        }
        if(order.getMainOrderOwner() != loggedInUserEmail){
            throw new CustomException(StatusCodes.UN_AUTHORIZED,"Cannot delete an order that doesn't belong to you");
        }
        if(order.getOrderStatus() != OrderStatus.preparing){
            throw new CustomException(StatusCodes.FORBIDDEN,"Cannot Cancel an "+order.getOrderStatus()+" Order");
        }
        orderDataAccess.deleteOrder(orderBodyRequest.id());
        notificationSchedulerService.scheduleNotification(loggedInUserEmail, notificationType, TemplatesFactory.cancelOrderMessage(loggedInUserEmail, orderBodyRequest.id()));
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