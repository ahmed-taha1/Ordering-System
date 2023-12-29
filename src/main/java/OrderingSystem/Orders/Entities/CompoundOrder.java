package OrderingSystem.Orders.Entities;

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
    public List<SimpleOrder> getOrderDetails() {
//       return orderComponents.stream()
//               .flatMap(order -> order.getOrderDetails().stream())
//               .collect(Collectors.toList());
        return orderComponents;
    }

    @Override
    public List<String> getAllOrderOwners() {
        return orderComponents.stream()
                .map(SimpleOrder::getMainOrderOwner)
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
