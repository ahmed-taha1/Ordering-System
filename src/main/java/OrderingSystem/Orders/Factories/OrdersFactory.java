package OrderingSystem.Orders.Factories;

import OrderingSystem.Orders.Entities.CompoundOrder;
import OrderingSystem.Orders.Entities.IOrderComponent;
import OrderingSystem.Orders.Entities.OrderType;
import OrderingSystem.Orders.Entities.SimpleOrder;

import java.util.*;
import java.util.function.Function;

public class OrdersFactory {
    private static Map<OrderType, Function<FactoryParams, IOrderComponent>> ordersMapping;
    private static OrdersFactory ordersFactoryInstance = null;
    private OrdersFactory(){
        ordersMapping = new HashMap<>();
        ordersMapping.put(OrderType.simpleOrder, this::createSimpleOrderInstance);
        ordersMapping.put(OrderType.compoundOrder, this::createCompoundOrderInstance);
    }

    public static OrdersFactory getInstance() {
        if(ordersFactoryInstance == null){
            ordersFactoryInstance = new OrdersFactory();
        }
        return ordersFactoryInstance;
    }
    public IOrderComponent createOrder(OrderType orderType,String mainOwnerEmail, List<SimpleOrder> orders){
        Function<FactoryParams, IOrderComponent> orderCreationFunction = ordersMapping.get(orderType);
        if(orderCreationFunction != null){
            return orderCreationFunction.apply(new FactoryParams(orders,mainOwnerEmail));
        }
        return null;
    }
    private IOrderComponent createSimpleOrderInstance(FactoryParams params){
        return new SimpleOrder(params.mainOwnerEmail,params.orders.get(0).getDeliveryAddress(),params.orders.get(0).getProducts());
    }

    private IOrderComponent createCompoundOrderInstance(FactoryParams params){
        return new CompoundOrder(params.mainOwnerEmail,params.orders);
    }
    private record FactoryParams(
            List<SimpleOrder> orders,
            String mainOwnerEmail
    ){
    }
}
