package OrderingSystem.Products;

import OrderingSystem.OrderingSystemApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "products")
public class ProductsController {
    private final IProductsDataAccess productsDataAccess = OrderingSystemApplication.getProductsDataAccess();

    @GetMapping("getProducts")
    public Collection<Product> getProducts(){
        return productsDataAccess.getProductsList();
    }

    @GetMapping("getProductBySerialNumber")
    public Product getProductBySerialNumber(@RequestBody getProductBySerialNumberBodyRequest request){
        return productsDataAccess.getProductBySerialNumber(request.serialNumber);
    }

    record getProductBySerialNumberBodyRequest(
            String serialNumber
    ){}
}
