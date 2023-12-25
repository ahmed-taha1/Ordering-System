package OrderingSystem;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Services.AuthenticationService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = AuthenticationService.class)
public class OrderingSystemApplication {
    public static Customer activeUser = null;
    public static void main(String[] args) {
        SpringApplication.run(OrderingSystemApplication.class, args);
    }

}
