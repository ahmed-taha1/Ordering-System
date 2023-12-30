package OrderingSystem;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.DataAccess.ICustomersDataAccess;
import OrderingSystem.Customer.DataAccess.InMemoryCustomersDataAccess;
import OrderingSystem.Notification.Entities.NotificationType;
import OrderingSystem.Notification.NotificationSchedulerService;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.DataAccess.InMemoryOrdersDataAccess;
import OrderingSystem.Orders.Factories.OrdersFactory;
import OrderingSystem.Products.DataAccess.IProductsDataAccess;
import OrderingSystem.Products.DataAccess.InMemoryProductsDataAccess;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

// todo add order statistics and notification stats
@SpringBootApplication
public class OrderingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderingSystemApplication.class, args);
    }
    private static Customer activeUser = null;
    private final static ICustomersDataAccess customersDataAccess = InMemoryCustomersDataAccess.getInstance();
    private final static IOrderDataAccess orderDataAccess = InMemoryOrdersDataAccess.getInstance();
    private final static IProductsDataAccess productsDataAccess = InMemoryProductsDataAccess.getInstance();
    private final static OrdersFactory ordersFactory = OrdersFactory.getInstance();
    private final static NotificationSchedulerService notificationSchedulerService = NotificationSchedulerService.getInstance();
    private final static NotificationType notificationType = NotificationType.email;
    public static IOrderDataAccess getOrderDataAccess() {
        return orderDataAccess;
    }
    public static ICustomersDataAccess getCustomersDataAccess() {
        return customersDataAccess;
    }
    public static IProductsDataAccess getProductsDataAccess(){
        return productsDataAccess;
    }
    public static Customer getActiveUser() {
        return activeUser;
    }
    public static void setActiveUser(Customer activeUser) {
        OrderingSystemApplication.activeUser = activeUser;
    }
    public static OrdersFactory getOrdersFactory(){
        return ordersFactory;
    }
    public static NotificationType getNotificationType(){return  notificationType;}
    public static NotificationSchedulerService getNotificationSchedulerService(){
        return notificationSchedulerService;
    }
}
