package OrderingSystem;

import OrderingSystem.Customer.Customer;
import Services.AuthenticationService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@ComponentScan(basePackageClasses = AuthenticationService.class)
public class OrderingSystemApplication {
    public static Customer activeUser = null;
    public static void main(String[] args) {
        SpringApplication.run(OrderingSystemApplication.class, args);
    }

}
