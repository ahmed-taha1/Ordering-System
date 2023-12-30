package OrderingSystem.Notification.factories;
import java.util.Collection;

public class TemplatesFactory {
    public static String createPlaceOrderMessage(String name, Collection<String> productsNames){
        String message = "Dear " + name + " , your booking of the: ";
        for(String productName : productsNames){
            message += productName + ", ";
        }
        message += "has been placed and awaiting for checkout, thanks for using our store :)";
        return message;
    }

    public static String createCheckoutMessage(String name, double totalCost, double shippingCost){
        String message = "Dear " + name + " , order has been successfully paid, with total cost: " + totalCost + ", shipping cost: " + shippingCost ;
        message += " thanks for using our store :)";
        return message;
    }
    public static String cancelOrderMessage(String name, int id){
        String message = "Dear " + name + " , your order that with id: " + id + " has been canceled";
        message += " thanks for using our store :)";
        return message;
    }
}
