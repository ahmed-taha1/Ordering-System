package OrderingSystem;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.Customer.InMemoryCustomersDataAccess;
import OrderingSystem.Orders.DataAccess.IOrderDataAccess;
import OrderingSystem.Orders.DataAccess.InMemoryOrdersDataAccess;
import OrderingSystem.Products.IProductsDataAccess;
import OrderingSystem.Products.InMemoryProductsDataAccess;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class OrderingSystemApplication {
    public static Customer activeUser = null;
    private final static ICustomersDataAccess customersDataAccess = InMemoryCustomersDataAccess.getInstance();
    private final static IOrderDataAccess orderDataAccess = InMemoryOrdersDataAccess.getInstance();
    private final static IProductsDataAccess productsDataAccess = InMemoryProductsDataAccess.getInstance();
    public static void main(String[] args) {
        SpringApplication.run(OrderingSystemApplication.class, args);
    }

    public static IOrderDataAccess getOrderDataAccess() {
        return orderDataAccess;
    }

    public static ICustomersDataAccess getCustomersDataAccess() {
        return customersDataAccess;
    }
    public static IProductsDataAccess getProductsDataAccess(){
        return productsDataAccess;
    }
}
