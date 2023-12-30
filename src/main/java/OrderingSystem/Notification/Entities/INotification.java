package OrderingSystem.Notification.Entities;

public interface INotification {
    void sendNotification();
    TemplateMessage getMessage();
    String getReceiver();
}
