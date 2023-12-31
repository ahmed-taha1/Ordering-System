package OrderingSystem.Notification.Service;

import OrderingSystem.Notification.DataAccess.INotificationDataAccess;
import OrderingSystem.Notification.Entities.INotification;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.Entities.NotificationQueue;
import OrderingSystem.Notification.Entities.TemplateMessage;
import OrderingSystem.Notification.factories.NotificationFactory;
import OrderingSystem.OrderingSystemApplication;

import java.util.Collection;

public class NotificationSchedulerService extends Thread{
    private static final NotificationFactory notificationFactory = NotificationFactory.getInstance();
    private static final NotificationQueue notificationQueue = new NotificationQueue();
    private static final INotificationDataAccess notificationDataAccess = OrderingSystemApplication.getNotificationDataAccess();
    private static final int WAIT_TIME = 20000;
    private static NotificationSchedulerService instance = null;
    private boolean schedulerStarted ;
    private NotificationSchedulerService(){
        schedulerStarted = false;
    }
    public static NotificationSchedulerService getInstance(){
        if(instance == null){
            instance = new NotificationSchedulerService();
        }
        return instance;
    }
    private void sendNotification(){
        while (true){
            try {
                Thread.sleep(WAIT_TIME);
                INotification notificationToBeSent = notificationQueue.getNextNotification();
                if(notificationToBeSent != null){
                    notificationToBeSent.sendNotification();
                    NotificationStatisticsService.storeNotification(notificationToBeSent);
                }
            }catch (Exception exception){
                System.out.println("Error when sleeping thread to schedule Notification Queue");
            }
        }

    }
    public void scheduleNotification(String receiverEmail, NotificationType notificationType, TemplateMessage templateMessage){
        INotification notification = notificationFactory.createNotificationService(notificationType,"OrdersAppSystem@gmail.com",receiverEmail,templateMessage);
        notificationQueue.addNotification(notification);
        if(!schedulerStarted){
            schedulerStarted = true;
            start();
        }
    }
    public Collection<TemplateMessage> getQueuedMessages(){
        return notificationQueue.getQueuedMessages();
    }
    @Override
    public void run() {
        super.run();
        sendNotification();
    }
}