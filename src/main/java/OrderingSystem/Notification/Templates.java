package OrderingSystem.Notification;
import java.util.Collection;

public class Templates {
    public static String createPlaceOrderMessage(String name, Collection<String> productsNames){
        String message = "Dear " + name + " , your booking of the: ";
        for(String productName : productsNames){
            message += productName + ", ";
        }
        message += "has been placed and awaiting for checkout, thanks for using our store :)";
        return message;
    }

    public static String createCheckoutMessage(String name, double totalCost, double shippingCost, double newBalance){
        String message = "Dear " + name + " , order has been successfully paid, with total cost: " + totalCost + ", shipping cost: " + shippingCost + ", your new balance is: " + newBalance;
        message += " thanks for using our store :)";
        return message;
    }
}
