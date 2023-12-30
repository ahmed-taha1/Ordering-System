package OrderingSystem.Notification.factories;
import OrderingSystem.Notification.Entities.TemplateMessage;
import OrderingSystem.Notification.Entities.TemplateType;

import java.util.Collection;

public class TemplatesFactory {
    public static TemplateMessage createPlaceOrderMessage(String name, Collection<String> productsNames){
        String message = "Dear " + name + " , your booking of the: ";
        for(String productName : productsNames){
            message += productName + ", ";
        }
        message += "has been placed and awaiting for checkout, thanks for using our store :)";
        return new TemplateMessage(message,TemplateType.placeOrderTemplate);
    }

    public static TemplateMessage createCheckoutMessage(String name, double totalCost, double shippingCost){
        String message = "Dear " + name + " , order has been successfully paid, with total cost: " + totalCost + ", shipping cost: " + shippingCost ;
        message += " thanks for using our store :)";
        return new TemplateMessage(message,TemplateType.checkoutOrderTemplate);
    }
    public static TemplateMessage cancelOrderMessage(String name, int id){
        String message = "Dear " + name + " , your order that with id: " + id + " has been canceled";
        message += " thanks for using our store :)";
        return new TemplateMessage(message,TemplateType.cancelOrderTemplate);
    }
}
