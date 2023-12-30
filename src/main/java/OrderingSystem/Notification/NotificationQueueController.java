package OrderingSystem.Notification;

import OrderingSystem.OrderingSystemApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path = "Notifications")
public class NotificationQueueController {
    NotificationSchedulerService notificationSchedulerService = OrderingSystemApplication.getNotificationSchedulerService();
    @GetMapping(path = "/")
    public ResponseEntity<Object> getQueuedMessage(){
        Collection<String>queuedMessage = notificationSchedulerService.getQueuedMessages();
        return ResponseEntity.ok(Map.of("notificationsQueue", queuedMessage));
    }
}
