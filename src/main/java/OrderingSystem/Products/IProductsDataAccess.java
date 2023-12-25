package OrderingSystem.Products;

public interface IProductsDataAccess {
    Product getProductByName(String productName);
    Product getProductBySerialNumber(String serialNumber);
    String getProductsList();
}