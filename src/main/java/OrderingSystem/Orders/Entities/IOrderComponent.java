package OrderingSystem.Orders.Entities;


import java.util.Collection;
import java.util.List;

public interface IOrderComponent {
    public int getId();
    public void setId(int id);
    public double getTotalCost();
    public OrderType getOrderType();
    public List<OrderDetails>getOrderDetails();
    public List<SimpleOrder>getOrders();
    public List<String> getAllOrderOwners();
    public OrderStatus getOrderStatus();
    public void updateOrderStatus(OrderStatus status);
    public String getMainOrderOwner();
    public Collection<String>getProductsNames();
}