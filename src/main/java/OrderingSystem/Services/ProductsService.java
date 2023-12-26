package OrderingSystem.Services;

import OrderingSystem.Products.IProductsDataAccess;
import OrderingSystem.Products.InMemoryProductsDataAccess;
import OrderingSystem.Products.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "orderingSystem/products")
public class ProductsService {
    private IProductsDataAccess productsDataAccess = InMemoryProductsDataAccess.getInstance();

    @GetMapping("getProducts")
    public Collection<Product> getProducts(){
        return productsDataAccess.getProductsList();
    }
}
