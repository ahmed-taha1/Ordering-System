package OrderingSystem.Shipping.Entities;

import OrderingSystem.Address.Address;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.SimpleOrder;

public interface IShippingCalculator {
    public double calculateShippingCost(SimpleOrder orderComponent) throws Exception;
}
