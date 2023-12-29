package OrderingSystem.Orders.Controller;

import OrderingSystem.Address.Address;
import OrderingSystem.Orders.Entities.*;

import java.util.Collection;
import java.util.List;

public class ResponseBodyRecords {
    public record PlaceOrderBodyResponse(
            int id,
            String message
    ){}
    public record getOrderDetailsResponse(
            String mainOrderOwner,
            Collection<OrderDetails> ordersDetails,
            double totalPrice,
            String message
    ){}
    public record getOrderDetailsResponseTest(
            List<OrderItem> products,
            String ownerEmail,
            OrderStatus status,
            Address deliveryAddress,
            int id
    ){}
    public record placeOrderFailedRecord(
            String message
    ){}
}