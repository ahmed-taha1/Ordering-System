package OrderingSystem.Shipping.Entities;

import OrderingSystem.Orders.Entities.SimpleOrder;

import java.util.Random;

public class SimpleOrderShippingCalculator implements IShippingCalculator{
    static int MIN_SHIPPING = 50;
    static int MAX_SHIPPING = 150;
    @Override
    public double calculateShippingCost(SimpleOrder orderComponent) {
        return new Random().nextDouble(MAX_SHIPPING - MIN_SHIPPING + 1) + MIN_SHIPPING;
    }
}
