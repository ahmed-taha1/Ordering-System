package OrderingSystem.Orders.Entities;

import OrderingSystem.Address.Address;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleOrder implements IOrderComponent{
    private final List<OrderItem>products;
    private final String ownerEmail;
    private OrderStatus status;
    private final Address deliveryAddress;
    private int id;
    public SimpleOrder(String ownerEmail,Address deliveryAddress,List<OrderItem> products){
        this.ownerEmail = ownerEmail;
        this.deliveryAddress = deliveryAddress;
        this.products = products;
        this.status = OrderStatus.preparing;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public  int getId(){
        return this.id;
    }
    @Override
    public OrderType getOrderType() {
        return OrderType.simpleOrder;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public double getTotalCost() {
        return products.stream()
                .mapToDouble(OrderItem::getItemPrice)
                .sum();
    }
    public List<SimpleOrder>getOrders(){
        return List.of(this);
    }
    public List<OrderItem> getProducts() {
        return products;
    }
    @Override
    public Collection<String> getProductsNames() {
        return products.stream()
                .map(orderItem -> orderItem.getProduct().getName())
                .collect(Collectors.toList());
    }
    @Override
    public List<OrderDetails> getOrderDetails() {
        return List.of(new OrderDetails(ownerEmail,deliveryAddress,status,products, getTotalCost()));
    }
    @Override
    public List<String> getAllOrderOwners() {
        return null;
    }

    @Override
    public OrderStatus getOrderStatus() {
        return status;
    }
    @Override
    public void updateOrderStatus(OrderStatus status) {
        this.status = status;
    }
    public String getMainOrderOwner(){
        return this.ownerEmail;
    }
}