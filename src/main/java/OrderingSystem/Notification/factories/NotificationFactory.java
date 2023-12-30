package OrderingSystem.Notification.factories;

import OrderingSystem.Notification.Entities.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NotificationFactory {
    private static NotificationFactory factoryInstance = null;
//    private Map<NotificationType,INotification>notificationStrategies ;
    private NotificationFactory(){
//        notificationStrategies = new HashMap<>();
//        notificationStrategies.put(NotificationType.email, )
    }
    public static NotificationFactory getInstance(){
        if(factoryInstance == null){
            factoryInstance = new NotificationFactory();
        }
        return factoryInstance;
    }
    public INotification createNotificationService(NotificationType notificationType,String sender,String receiver, TemplateMessage message){
        if(Objects.equals(notificationType, NotificationType.email)){
            return new EmailNotification(sender,receiver, message);
        }
        if(Objects.equals(notificationType, NotificationType.sms)){
            return new SMSNotification(sender,receiver, message);
        }
        return null;
    }
}
