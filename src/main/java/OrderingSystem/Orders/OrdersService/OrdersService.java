package OrderingSystem.Orders.OrdersService;


import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Orders.Controller.RequestBodyRecords;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.Entities.*;
import OrderingSystem.Orders.Factories.OrdersFactory;
import OrderingSystem.Products.Entities.Product;
import OrderingSystem.Products.Service.ProductsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdersService {
    private static final IOrderDataAccess orderDataAccess = OrderingSystemApplication.getOrderDataAccess();
    private static final OrdersFactory ordersFactory = OrderingSystemApplication.getOrdersFactory();
    public static int placeOrder(RequestBodyRecords.PlaceOrderBodyRequest orderBodyRequest, String mainOwnerEmail){
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
            orders.add(new SimpleOrder(orderDetails.orderOwnerEmail(),orderDetails.address(),items));
        }
        IOrderComponent order = ordersFactory.createOrder(orderType,mainOwnerEmail,orders);
        orderDataAccess.insertOrder(order);
        return order.getId();
    }
    public static IOrderComponent findOrder(int orderId){
        return orderDataAccess.getOrderById(orderId);
    }
}