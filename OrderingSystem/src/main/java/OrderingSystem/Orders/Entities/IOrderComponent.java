package OrderingSystem.Orders.Entities;


import OrderingSystem.Address.Address;

import java.util.Collection;
import java.util.List;

public interface IOrderComponent {
    public int getId();
    public void setId(int id);
    public void addOrder(SimpleOrder order);
    public double getPrice();
    public OrderType getOrderType();
    public Collection<OrderDetails>getOrderDetail();
    public Collection<String> getOrderOwner();
    public OrderStatus getOrderStatus();
    public void updateOrderStatus(OrderStatus status);
    public Address getDeliveryAddress();
}
