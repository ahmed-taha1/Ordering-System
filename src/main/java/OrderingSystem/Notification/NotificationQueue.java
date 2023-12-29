package OrderingSystem.Notification;

import OrderingSystem.Notification.Entities.INotification;

import java.util.ArrayDeque;
import java.util.Queue;

public class NotificationQueue {
    private static final Queue<INotification>notifications = new ArrayDeque<>();
    public INotification getNextNotification(){
        if(notifications.isEmpty()){
            return null;
        }
        return notifications.poll();
    }
    public void addNotification(INotification notification){
        notifications.add(notification);
    }
}
