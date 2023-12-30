package OrderingSystem.Notification;

import OrderingSystem.Notification.Entities.INotification;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.stream.Collectors;

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
    Collection<String> getQueuedMessages(){
        return notifications.stream()
                .map(INotification::getMessage)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
