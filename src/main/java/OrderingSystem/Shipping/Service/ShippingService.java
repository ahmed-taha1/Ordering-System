package OrderingSystem.Shipping.Service;

import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Shipping.Entities.IShippingCalculator;
import OrderingSystem.Shipping.Factories.ShippingCalculatorFactory;

public class ShippingService {
    private static final ShippingCalculatorFactory shippingCalculatorFactory = ShippingCalculatorFactory.getInstance();
    public static double calculateShippingCost(SimpleOrder order){
        IShippingCalculator shippingCalculator = shippingCalculatorFactory.createShippingStrategy(order.getOrderType());
        return shippingCalculator.calculateShippingCost(order);
    }
}