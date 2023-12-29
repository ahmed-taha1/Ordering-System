package OrderingSystem.Products.Controller;

import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Products.DataAccess.IProductsDataAccess;
import OrderingSystem.Products.DataAccess.InMemoryProductsDataAccess;
import OrderingSystem.Products.Entities.Product;
import OrderingSystem.Products.Service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path = "products")
public class ProductsController {
    @GetMapping("getProducts")
    public Map<String, Object> getProducts(){
        return Map.of("products", ProductsService.fetchProducts());
    }

    @GetMapping("getProductBySerialNumber")
    public Map<String, Object> getProductBySerialNumber(@RequestBody getProductBySerialNumberBodyRequest request){
        return Map.of("product", ProductsService.findProductBySerialNumber(request.serialNumber));
    }

    @GetMapping("/categories")
    public Map<String, Object> getProductsCategoryCount(){
        return Map.of("categories", ProductsService.fetchProductsCount());
    }
    record getProductBySerialNumberBodyRequest(
            String serialNumber
    ){}
}
