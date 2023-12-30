package OrderingSystem.Notification.DataAccess;

import OrderingSystem.Notification.Entities.TemplateType;

public interface INotificationDataAccess {
    String getMostNotifiedReceiver();
    TemplateType getMostSentNotificationTemplate();
    void addNotifiedReceiver(String receiver);
    void addNotifiedTemplate(TemplateType templateType);
}
