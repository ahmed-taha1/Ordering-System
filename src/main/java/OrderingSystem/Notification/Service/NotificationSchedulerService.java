package OrderingSystem.Notification.Service;

import OrderingSystem.Notification.Entities.INotification;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.Entities.NotificationQueue;
import OrderingSystem.Notification.factories.NotificationFactory;

import java.util.Collection;

public class NotificationSchedulerService extends Thread{
    private static final NotificationFactory notificationFactory = NotificationFactory.getInstance();
    private static final NotificationQueue notificationQueue = new NotificationQueue();
    private static final int WAIT_TIME = 10000;
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
                }
            }catch (Exception exception){
                System.out.println("Error when sleeping thread to schedule Notification Queue");
            }
        }

    }
    public void scheduleNotification(String receiverEmail, NotificationType notificationType, String message){
        INotification notification = notificationFactory.createNotificationService(notificationType,"OrdersAppSystem@gmail.com",receiverEmail,message);
        notificationQueue.addNotification(notification);
        if(!schedulerStarted){
            schedulerStarted = true;
            start();
        }
    }
    public Collection<String> getQueuedMessages(){
        System.out.println("Getting queue");
        return notificationQueue.getQueuedMessages();
    }
    @Override
    public void run() {
        super.run();
        sendNotification();
    }
}