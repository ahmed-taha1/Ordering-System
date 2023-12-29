package OrderingSystem.Shipping.Service;

import OrderingSystem.Exceptions.CustomException;
import OrderingSystem.Orders.Entities.SimpleOrder;
import OrderingSystem.Shipping.Entities.IShippingCalculator;
import OrderingSystem.Shipping.Factories.ShippingCalculatorFactory;
import OrderingSystem.StatusCodes.StatusCodes;

public class ShippingService {
    private static final ShippingCalculatorFactory shippingCalculatorFactory = ShippingCalculatorFactory.getInstance();
    public static double calculateShippingCost(SimpleOrder order) throws CustomException {
        IShippingCalculator shippingCalculator = shippingCalculatorFactory.createShippingStrategy(order.getOrderType());
        if(shippingCalculator == null){
            throw new CustomException(StatusCodes.BAD_REQUEST, "order type not support");
        }
        return shippingCalculator.calculateShippingCost(order);
    }
}