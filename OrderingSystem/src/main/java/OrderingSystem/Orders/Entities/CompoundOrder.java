package OrderingSystem.Orders.Entities;

import OrderingSystem.Address.Address;
import org.springframework.core.annotation.Order;

import java.util.Collection;
import java.util.stream.Collectors;

public class CompoundOrder implements IOrderComponent{
    private final Collection<SimpleOrder>orderComponents;
    private OrderStatus status;
    private int id;
    public CompoundOrder(Collection<SimpleOrder> orderComponents, Address deliveryAddress) {
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
    public double getPrice() {
        return orderComponents.stream()
                .mapToDouble(SimpleOrder::getPrice)
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
    public Collection<OrderDetails> getOrderDetail() {
       return orderComponents.stream()
               .flatMap(order -> order.getOrderDetail().stream())
               .collect(Collectors.toList());
    }
    @Override
    public Collection<String> getOrderOwner() {
        return orderComponents.stream()
                .flatMap(order -> order.getOrderOwner().stream())
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
