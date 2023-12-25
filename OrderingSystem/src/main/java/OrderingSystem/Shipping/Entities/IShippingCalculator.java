package OrderingSystem.Shipping.Entities;

import OrderingSystem.Orders.Entities.SimpleOrder;

public interface IShippingCalculator {
    public double calculateShippingCost(SimpleOrder orderComponent) throws Exception;
}
