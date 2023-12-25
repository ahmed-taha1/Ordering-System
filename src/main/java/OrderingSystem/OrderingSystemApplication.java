package OrderingSystem;

import OrderingSystem.Customer.Customer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class OrderingSystemApplication {
    public static Customer activeUser = null;
    public static void main(String[] args) {
        SpringApplication.run(OrderingSystemApplication.class, args);
    }
}
