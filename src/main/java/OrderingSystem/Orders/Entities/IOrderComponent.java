package OrderingSystem.Orders.Entities;


import java.util.Collection;
import java.util.List;

public interface IOrderComponent {
    public int getId();
    public void setId(int id);
    public void addOrder(SimpleOrder order);
    public double getTotalPrice();
    public OrderType getOrderType();
    public List<SimpleOrder> getOrderDetails();
    public List<String> getAllOrderOwners();
    public OrderStatus getOrderStatus();
    public void updateOrderStatus(OrderStatus status);
    public String getMainOrderOwner();
}