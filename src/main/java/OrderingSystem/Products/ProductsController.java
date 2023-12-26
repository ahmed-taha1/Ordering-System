package OrderingSystem.Products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "products")
public class ProductsController {
    private IProductsDataAccess productsDataAccess = InMemoryProductsDataAccess.getInstance();

    @GetMapping("")
    public Collection<Product> getProducts(){
        return productsDataAccess.getProductsList();
    }
}
