package OrderingSystem.Orders.Entities;


import OrderingSystem.Address.Address;
import OrderingSystem.Products.Product;

import java.util.Collection;

public class OrderDetails {
    private final Address deliveryAddress;
    private final Collection<Product>products;
    private final String orderOwnerEmail;
    private final int orderId;
    public OrderDetails(int orderId,String orderOwnerEmail,Address deliveryAddress, Collection<Product> products ) {
        this.orderId = orderId;
        this.deliveryAddress = deliveryAddress;
        this.products = products;
        this.orderOwnerEmail = orderOwnerEmail;
    }
    public Collection<Product> getProducts() {
        return products;
    }
    public String getOrderOwnerEmail() {
        return orderOwnerEmail;
    }
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }
    public int getOrderId() {
        return orderId;
    }
}
