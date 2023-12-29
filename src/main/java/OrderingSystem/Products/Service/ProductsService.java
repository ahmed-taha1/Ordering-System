package OrderingSystem.Products.Service;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Products.DataAccess.IProductsDataAccess;
import OrderingSystem.Products.Entities.Product;
import java.util.Collection;
import java.util.Map;

public class ProductsService {
    private static final IProductsDataAccess productsDataAccess = OrderingSystemApplication.getProductsDataAccess();
    public static Map<String, Integer> fetchProductsCount(){
        return productsDataAccess.getCategoriesCount();
    }
    public static Collection<Product> fetchProducts(){
        return productsDataAccess.getProductsList();
    }
    public static Product findProductBySerialNumber(String serialNumber){
        return productsDataAccess.getProductBySerialNumber(serialNumber);
    }
}