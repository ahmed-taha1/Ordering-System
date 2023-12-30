package OrderingSystem.Notification.Controller;

import OrderingSystem.Notification.Entities.TemplateMessage;
import OrderingSystem.Notification.Entities.TemplateType;
import OrderingSystem.Notification.Service.NotificationSchedulerService;
import OrderingSystem.Notification.Service.NotificationStatisticsService;
import OrderingSystem.OrderingSystemApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "notifications")
public class NotificationsController {
    NotificationSchedulerService notificationSchedulerService = OrderingSystemApplication.getNotificationSchedulerService();
    @GetMapping(path = "/")
    public ResponseEntity<Object> getQueuedMessage(){
        Collection<TemplateMessage>queuedMessage = notificationSchedulerService.getQueuedMessages();
        return ResponseEntity.ok(Map.of("notificationsQueue", queuedMessage));
    }
    @GetMapping(path = "statistics")
    public ResponseEntity<Object>getNotificationsStatistics(){
        String mostNotifiedReceiver = NotificationStatisticsService.getMostNotifiedReceiver();
        TemplateType mostUsedTemplate = NotificationStatisticsService.getMostSentTemplate();
        Map<String, Object> response = new HashMap<>();
        response.put("mostNotifiedReceiver", mostNotifiedReceiver);
        response.put("mostUsedTemplate", mostUsedTemplate);
        return ResponseEntity.ok().body(response);
    }
}