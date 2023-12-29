package OrderingSystem;
import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.Customer.InMemoryCustomersDataAccess;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.DataAccess.InMemoryOrdersDataAccess;
import OrderingSystem.Orders.Factories.OrdersFactory;
import OrderingSystem.Products.DataAccess.IProductsDataAccess;
import OrderingSystem.Products.DataAccess.InMemoryProductsDataAccess;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


// todo check if products exist , if not throw Exception to be handled by controller
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
}
