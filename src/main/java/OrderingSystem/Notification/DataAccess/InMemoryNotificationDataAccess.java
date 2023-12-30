package OrderingSystem.Notification.DataAccess;

import OrderingSystem.Notification.Entities.TemplateType;
import OrderingSystem.Orders.DataAccess.InMemoryOrdersDataAccess;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryNotificationDataAccess implements INotificationDataAccess{
    private static Map<String, Integer> notifiedReceiversCounterDB;
    private static Map<TemplateType, Integer> notifiedTemplatesCounterDB;
    private static InMemoryNotificationDataAccess inMemoryOrdersDataAccessInstance = null;

    public static InMemoryNotificationDataAccess getInstance() {
        if(inMemoryOrdersDataAccessInstance == null){
            inMemoryOrdersDataAccessInstance = new InMemoryNotificationDataAccess();
        }
        return inMemoryOrdersDataAccessInstance;
    }
    private InMemoryNotificationDataAccess(){
        notifiedReceiversCounterDB = new HashMap<>();
        notifiedTemplatesCounterDB = new HashMap<>();
    }
    @Override
    public String getMostNotifiedReceiver() {
        Optional<Map.Entry<String, Integer>> mostNotifiedEmail = notifiedReceiversCounterDB.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return mostNotifiedEmail.map(Map.Entry::getKey).orElse(null);
    }

    @Override
    public TemplateType getMostSentNotificationTemplate() {
        Optional<Map.Entry<TemplateType, Integer>> mostSentTemplate = notifiedTemplatesCounterDB.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return mostSentTemplate.map(Map.Entry::getKey).orElse(null);
    }

    @Override
    public void addNotifiedReceiver(String receiver) {
        notifiedReceiversCounterDB.merge(receiver, 1, Integer::sum);
    }

    @Override
    public void addNotifiedTemplate(TemplateType templateType) {
        notifiedTemplatesCounterDB.merge(templateType, 1, Integer::sum);
    }
}
