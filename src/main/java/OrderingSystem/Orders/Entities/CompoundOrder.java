package OrderingSystem.Orders.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompoundOrder implements IOrderComponent{
    private final List<SimpleOrder> orderComponents;
    private OrderStatus status;
    private int id;
    private final String ownerEmail ;
    public CompoundOrder(String ownerEmail,List<SimpleOrder> orderComponents) {
        this.ownerEmail = ownerEmail;
        this.orderComponents = orderComponents;
        this.status = OrderStatus.preparing;
    }
    @Override
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public double getTotalCost() {
        return orderComponents.stream()
                .mapToDouble(SimpleOrder::getTotalCost)
                .sum();
    }
    @Override
    public OrderType getOrderType() {
        return OrderType.compoundOrder;
    }
    @Override
    public List<OrderDetails> getOrderDetails() {
       return orderComponents.stream()
               .flatMap(order -> order.getOrderDetails().stream())
               .collect(Collectors.toList());
    }
    public List<SimpleOrder>getOrders(){
        return orderComponents;
    }
    @Override
    public List<String> getAllOrderOwners() {
        return orderComponents.stream()
                .map(SimpleOrder::getMainOrderOwner)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<String> getProductsNames(){
        return orderComponents.stream()
                .flatMap(order -> order.getProductsNames().stream())
                .collect(Collectors.toList());
    }

    @Override
    public OrderStatus getOrderStatus() {
        return status;
    }
    @Override
    public void updateOrderStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String getMainOrderOwner() {
        return ownerEmail;
    }
}
