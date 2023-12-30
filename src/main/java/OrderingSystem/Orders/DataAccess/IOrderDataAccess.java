package OrderingSystem.Orders.DataAccess;

import OrderingSystem.Orders.Entities.IOrderComponent;

import java.util.Collection;

public interface IOrderDataAccess {
    IOrderComponent getOrderById(int orderId);
    Collection<IOrderComponent>getCustomerOrders(String customerEmail);
    void insertOrder(IOrderComponent order);
    void updateOrder(IOrderComponent order);
    int getNextOrderID();
    void deleteOrder(int orderId);
}
