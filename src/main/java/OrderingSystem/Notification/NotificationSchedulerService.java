package OrderingSystem.Notification;

import OrderingSystem.Notification.Entities.INotification;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.factories.NotificationFactory;

public class NotificationSchedulerService /*extends Thread*/{
    private static final NotificationFactory notificationFactory = NotificationFactory.getInstance();
    private static final NotificationQueue notificationQueue = new NotificationQueue();
    private static final int WAIT_TIME = 5000;
    public static void scheduleNotification(String receiverEmail, NotificationType notificationType, String message){
        INotification notification = notificationFactory.createNotificationService(notificationType,"OrdersAppSystem@gmail.com",receiverEmail);
        notificationQueue.addNotification(notification);
        try {
            Thread.sleep(WAIT_TIME);
            INotification notificationToBeSent = notificationQueue.getNextNotification();
            if(notificationToBeSent != null){
                notification.sendNotification(message);
            }
        }catch (Exception exception){
            System.out.println("Error when sleeping thread to schedule Notification Queue");
        }
    }
}