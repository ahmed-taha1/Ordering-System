package OrderingSystem.Orders.Entities;

import OrderingSystem.Address.Address;
import OrderingSystem.Products.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleOrder implements IOrderComponent{
    private final List<OrderItem>products;
    private final String ownerEmail;
    private OrderStatus status;
    private final Address deliveryAddress;
    private int id;
    public SimpleOrder(String ownerEmail,Address deliveryAddress,Collection<OrderItem> products){
        this.ownerEmail = ownerEmail;
        this.deliveryAddress = deliveryAddress;
        this.products = new ArrayList<>();
        this.products.addAll(products);
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
    public void addOrder(SimpleOrder order) {
        return;
    }
    @Override
    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(OrderItem::getItemPrice)
                .sum();
    }

    public List<OrderItem> getProducts() {
        return products;
    }
    @Override
    public Collection<SimpleOrder> getOrderDetails() {
        return List.of(this);
    }
    @Override
    public Collection<String> getAllOrderOwners() {
        return List.of(this.ownerEmail);
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