package OrderingSystem.Products.Controller;

import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Products.DataAccess.IProductsDataAccess;
import OrderingSystem.Products.Entities.Product;
import OrderingSystem.Products.Service.ProductsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "products")
public class ProductsController {
    @GetMapping("getProducts")
    public ResponseBodyRecords.getProductsResponseBody getProducts(){
        return new ResponseBodyRecords.getProductsResponseBody(ProductsService.fetchProductsCount(), ProductsService.fetchProducts());
    }

    @GetMapping("getProductBySerialNumber")
    public Product getProductBySerialNumber(@RequestBody getProductBySerialNumberBodyRequest request){
        return ProductsService.findProductBySerialNumber(request.serialNumber);
    }

    record getProductBySerialNumberBodyRequest(
            String serialNumber
    ){}
}
