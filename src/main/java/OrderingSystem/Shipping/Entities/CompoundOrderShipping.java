package OrderingSystem.Shipping.Entities;

import OrderingSystem.Orders.Entities.SimpleOrder;

import java.util.Random;

public class CompoundOrderShipping implements IShippingCalculator{
    private static final int MIN_SHIPPING = 50;
    private static final int MAX_SHIPPING = 150;
    private static final int COMPOUND_DISCOUNT = 2;
    @Override
    public double calculateShippingCost(SimpleOrder orderComponent) throws Exception {
        return (new Random().nextDouble(MAX_SHIPPING - MIN_SHIPPING + 1) + MIN_SHIPPING) /  COMPOUND_DISCOUNT;
    }
}
