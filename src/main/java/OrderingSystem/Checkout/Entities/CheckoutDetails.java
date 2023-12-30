package OrderingSystem.Checkout.Entities;

import OrderingSystem.Orders.Entities.OrderStatus;

public class CheckoutDetails {
    private final String ownerName;
    private final double orderPrice;
    private final double shippingCost;
    private final String ownerEmail;
    private final OrderStatus orderStatus;
    public CheckoutDetails(String ownerName,String ownerMail,double orderPrice,double shippingCost,OrderStatus orderStatus){
        this.ownerName = ownerName;
        this.ownerEmail = ownerMail;
        this.orderPrice = orderPrice;
        this.shippingCost = shippingCost;
        this.orderStatus = orderStatus;
    }
    public String getOwnerName() {
        return ownerName;
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
