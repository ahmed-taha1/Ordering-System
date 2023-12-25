package OrderingSystem.Orders.Entities;

import java.util.Collection;
import java.util.stream.Collectors;

public class CompoundOrder implements IOrderComponent{
    private final Collection<SimpleOrder>orderComponents;
    private OrderStatus status;
    private int id;
    public CompoundOrder(Collection<SimpleOrder> orderComponents) {
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
    public double getTotalPrice() {
        return orderComponents.stream()
                .mapToDouble(SimpleOrder::getTotalPrice)
                .sum();
    }
    @Override
    public void addOrder(SimpleOrder order) {
        this.orderComponents.add(order);
    }
    @Override
    public OrderType getOrderType() {
        return OrderType.compoundOrder;
    }
    @Override
    public Collection<SimpleOrder> getOrderDetail() {
       return orderComponents.stream()
               .flatMap(order -> order.getOrderDetail().stream())
               .collect(Collectors.toList());
    }
    @Override
    public Collection<String> getAllOrderOwners() {
        return orderComponents.stream()
                .flatMap(order -> order.getAllOrderOwners().stream())
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
}
