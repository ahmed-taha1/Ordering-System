package OrderingSystem.Products.Controller;

import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Products.DataAccess.IProductsDataAccess;
import OrderingSystem.Products.DataAccess.InMemoryProductsDataAccess;
import OrderingSystem.Products.Entities.Product;
import OrderingSystem.Products.Service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object getProductBySerialNumber(@RequestBody getProductBySerialNumberBodyRequest request){
        Product product = ProductsService.findProductBySerialNumber(request.serialNumber);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("product", "null", "message", "product not found"));
        }
        return Map.of("product", product, "message", "product found");
    }

    @GetMapping("/categories")
    public Object getProductsCategoryCount(){
        return Map.of("categories", ProductsService.fetchProductsCount());
    }
    record getProductBySerialNumberBodyRequest(
            String serialNumber
    ){}
}
