package OrderingSystem.Shipping.Factories;

import OrderingSystem.Orders.Entities.OrderType;
import OrderingSystem.Shipping.Entities.IShippingCalculator;

import java.util.HashMap;
import java.util.Map;

public class ShippingCalculatorFactory {
    private static ShippingCalculatorFactory factoryInstance = null;
    private final Map<OrderType,IShippingCalculator> shippingCalculatorStrategies;
    public static ShippingCalculatorFactory getInstance(){
        if(factoryInstance == null){
            factoryInstance = new ShippingCalculatorFactory();
        }
        return factoryInstance;
    }
    private ShippingCalculatorFactory(){
        shippingCalculatorStrategies = new HashMap<>();
    }
    public IShippingCalculator createShippingStrategy(OrderType orderType){
        return shippingCalculatorStrategies.get(orderType);
    }
}
