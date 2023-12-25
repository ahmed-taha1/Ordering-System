package OrderingSystem.Services;

import OrderingSystem.Products.IProductsDataAccess;
import OrderingSystem.Products.InMemoryProductsDataAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "orderingSystem/products")
public class ProductsService {
    private IProductsDataAccess productsDataAccess = InMemoryProductsDataAccess.getInstance();

    @GetMapping("getProducts")
    public String getProducts(){
        return productsDataAccess.getProductsList();
    }
}
