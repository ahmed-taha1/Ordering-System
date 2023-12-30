package OrderingSystem.Notification.Service;

import OrderingSystem.Notification.DataAccess.INotificationDataAccess;
import OrderingSystem.Notification.Entities.INotification;
import OrderingSystem.Notification.Entities.TemplateType;
import OrderingSystem.OrderingSystemApplication;

public class NotificationStatisticsService {
    public static INotificationDataAccess notificationDataAccess = OrderingSystemApplication.getNotificationDataAccess();
    public static TemplateType getMostSentTemplate(){
        return notificationDataAccess.getMostSentNotificationTemplate();
    }
    public static String getMostNotifiedReceiver(){
        return notificationDataAccess.getMostNotifiedReceiver();
    }
    public static void storeNotification(INotification notification){
        notificationDataAccess.addNotifiedTemplate(notification.getMessage().getTemplateType());
        notificationDataAccess.addNotifiedReceiver(notification.getReceiver());
    }
}
