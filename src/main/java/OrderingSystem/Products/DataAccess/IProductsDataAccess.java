package OrderingSystem.Products.DataAccess;

import OrderingSystem.Products.Entities.Product;

import java.util.Collection;
import java.util.Map;

public interface IProductsDataAccess {
    Product getProductBySerialNumber(String serialNumber);
    Collection<Product> getProductsList();
    Map<String, Integer> getCategoriesCount();
}