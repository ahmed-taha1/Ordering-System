package OrderingSystem.Checkout;

public class RequestsBodyRecords {
    public static record CheckoutRequestBody(
            int orderId
    ){}
}
