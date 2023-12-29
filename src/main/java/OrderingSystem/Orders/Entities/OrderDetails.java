package OrderingSystem.Orders.Entities;

import OrderingSystem.Address.Address;

import java.util.List;

public class OrderDetails {
    private final List<OrderItem> products;
    private final String ownerEmail;
    private final OrderStatus status;
    private final Address deliveryAddress;
    private final Double totalCost ;
    public OrderDetails(String ownerEmail,Address deliveryAddress,OrderStatus status,List<OrderItem> products,Double cost){
        this.ownerEmail = ownerEmail;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.products = products;
        this.totalCost = cost;
    }
    public List<OrderItem> getProducts() {
        return products;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }
    public Double getTotalCost() {
        return totalCost;
    }
    public OrderStatus getOrderStatus(){return status;}
}
