package OrderingSystem.Products;

import java.util.Collection;

public interface IProductsDataAccess {
    Product getProductByName(String productName);
    Product getProductBySerialNumber(String serialNumber);
    Collection<Product> getProductsList();
}