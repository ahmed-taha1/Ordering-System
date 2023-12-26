package OrderingSystem.Orders;


import OrderingSystem.Orders.Controller.RequestBodyRecords;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.Entities.*;
import OrderingSystem.Orders.Factories.OrdersFactory;
import OrderingSystem.Products.IProductsDataAccess;
import OrderingSystem.Products.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlaceOrdersService {
    public static int placeOrder(RequestBodyRecords.PlaceOrderBodyRequest orderBodyRequest, String mainOwnerEmail,
                           OrdersFactory ordersFactory,
                           IProductsDataAccess productsDataAccess, IOrderDataAccess orderDataAccess){
        OrderType orderType = orderBodyRequest.orderType();
        Collection<RequestBodyRecords.OrderDetailsRecord> orderDetailsRecord = orderBodyRequest.orders();
        List<SimpleOrder> orders = new ArrayList<>();
        for (RequestBodyRecords.OrderDetailsRecord orderDetails : orderDetailsRecord) {
            Collection<RequestBodyRecords.OrderItemRecord>orderItemRecords = orderDetails.products();
            Collection<OrderItem>items = new ArrayList<>();
            for(RequestBodyRecords.OrderItemRecord orderItems :orderItemRecords){
                Product product = productsDataAccess.getProductBySerialNumber(orderItems.serialNumber());
                items.add(new OrderItem(product, orderItems.count()));
            }
            orders.add(new SimpleOrder(orderDetails.orderOwnerEmail(),orderDetails.address(),items));
        }
        IOrderComponent order = ordersFactory.createOrder(orderType,mainOwnerEmail,orders);
        orderDataAccess.insertOrder(order);
        return order.getId();
    }
}