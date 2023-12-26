package OrderingSystem.Checkout.Controller;

public class RequestsBodyRecords {
    public record CheckoutRequestBody(
            int orderId
    ){}
}
