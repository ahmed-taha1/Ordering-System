package OrderingSystem.Orders.Entities;


import java.util.Collection;

public interface IOrderComponent {
    public int getId();
    public void setId(int id);
    public void addOrder(SimpleOrder order);
    public double getTotalPrice();
    public OrderType getOrderType();
    public Collection<SimpleOrder> getOrderDetails();
    public Collection<String> getAllOrderOwners();
    public OrderStatus getOrderStatus();
    public void updateOrderStatus(OrderStatus status);
    public String getMainOrderOwner();
}