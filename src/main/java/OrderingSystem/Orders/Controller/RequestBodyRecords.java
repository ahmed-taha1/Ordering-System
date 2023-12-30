package OrderingSystem.Orders.Controller;
import OrderingSystem.Address.Address;
import OrderingSystem.Orders.Entities.OrderType;
import java.util.List;

public class RequestBodyRecords {

    public record PlaceOrderBodyRequest(
            OrderType orderType,
            List<OrderDetailsRecord> orders
    ){}
    public record OrderDetailsRecord(
            Address address,
            List<OrderItemRecord>products,
            String orderOwnerEmail
    ){}
    public record OrderItemRecord(
            String serialNumber,
            int count
    ){}
    public record GetOrderDetailsBodyRequest(
            int id
    ){}
    public record CancelOrderBodyRequest(
            int id
    ){}
}
