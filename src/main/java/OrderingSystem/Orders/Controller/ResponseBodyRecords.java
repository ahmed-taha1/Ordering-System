package OrderingSystem.Orders.Controller;

import OrderingSystem.Address.Address;
import OrderingSystem.Orders.Entities.OrderItem;
import OrderingSystem.Orders.Entities.OrderStatus;
import OrderingSystem.Orders.Entities.SimpleOrder;

import java.util.Collection;
import java.util.List;

public class ResponseBodyRecords {
    public record PlaceOrderBodyResponse(
            int id,
            String message
    ){}
    public record getOrderDetailsResponseeeee(
            Collection<SimpleOrder> order,
            String message
    ){}
    public record getOrderDetailsResponse(
            List<OrderItem> products,
            String ownerEmail,
            OrderStatus status,
            Address deliveryAddress,
            int id
    ){}
}