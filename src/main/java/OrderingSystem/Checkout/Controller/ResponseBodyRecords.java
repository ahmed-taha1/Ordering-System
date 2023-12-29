package OrderingSystem.Checkout.Controller;

import OrderingSystem.Checkout.Entities.CheckoutDetails;

import java.util.Collection;

public class ResponseBodyRecords{
    public record CheckoutDetailsRecord(
            Collection<CheckoutDetails> checkoutDetails,
            String message
    ){}
    public record CheckoutFailedRecord(
            String message
    ){}
}
