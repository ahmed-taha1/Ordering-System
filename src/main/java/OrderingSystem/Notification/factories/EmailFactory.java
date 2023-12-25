package OrderingSystem.Notification.factories;

import OrderingSystem.Notification.Entities.EmailNotification;
import OrderingSystem.Notification.Entities.INotification;
import OrderingSystem.Notification.Entities.SMSNotification;

import java.util.Objects;

public class EmailFactory {
    private static EmailFactory factoryInstance = null;
    private static EmailFactory getInstance(){
        if(factoryInstance == null){
            factoryInstance = new EmailFactory();
        }
        return factoryInstance;
    }
    private INotification createNotificationService(String notificationType,String sender,String receiver){
        if(Objects.equals(notificationType, "email")){
            return new EmailNotification(sender,receiver);
        }
        if(Objects.equals(notificationType, "sms")){
            return new SMSNotification(sender,receiver);
        }
        return null;
    }
}
