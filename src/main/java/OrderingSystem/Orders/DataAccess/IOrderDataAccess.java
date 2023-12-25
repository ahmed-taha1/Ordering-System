package OrderingSystem.Orders.DataAccess;

import OrderingSystem.Orders.Entities.IOrderComponent;

import java.util.Collection;

public interface IOrderDataAccess {
    IOrderComponent getOrderById(int orderId);
    Collection<IOrderComponent>getCustomerOrders(String customerEmail);
}