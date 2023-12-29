package OrderingSystem.Checkout.Entities;

import OrderingSystem.Orders.Entities.OrderStatus;

public class CheckoutDetails {
    private final double orderPrice;
    private final double shippingCost;
    private final String ownerEmail;
    private final OrderStatus orderStatus;
    public CheckoutDetails(String ownerMail,double orderPrice,double shippingCost,OrderStatus orderStatus){
        this.ownerEmail = ownerMail;
        this.orderPrice = orderPrice;
        this.shippingCost = shippingCost;
        this.orderStatus = orderStatus;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
    public double getShippingCost() {
        return shippingCost;
    }
    public double getOrderPrice() {
        return orderPrice;
    }
    public OrderStatus getOrderStatus(){return orderStatus;}
}
