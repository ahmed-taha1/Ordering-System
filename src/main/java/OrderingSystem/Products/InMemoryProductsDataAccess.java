package OrderingSystem.Products;

import java.util.*;

public class InMemoryProductsDataAccess implements IProductsDataAccess {
    Map<String, Product> productsDB;
    static InMemoryProductsDataAccess productInMemoryDataAccessInstance = null;

    private InMemoryProductsDataAccess(){
        productsDB = new HashMap<>();
        productsDB.put("1", new Product("1", "Samsung Galaxy S21", "Samsung", "Smartphones", 5000));
        productsDB.put("2", new Product("2", "Apple MacBook Pro", "Apple", "Laptops", 10000));
        productsDB.put("3", new Product("3", "Sony PlayStation 5", "Sony", "Gaming Consoles", 15000));
        productsDB.put("4", new Product("4", "Bose QuietComfort 35 II", "Bose", "Headphones", 20000));
        productsDB.put("5", new Product("5", "LG OLED C1 Series TV", "LG", "Televisions", 25000));
        productsDB.put("6", new Product("6", "Dell XPS 13", "Dell", "Laptops", 30000));
        productsDB.put("7", new Product("7", "Canon EOS R5", "Canon", "Cameras", 35000));
        productsDB.put("8", new Product("8", "Nike Air Zoom Pegasus", "Nike", "Shoes", 40000));
        productsDB.put("9", new Product("9", "KitchenAid Stand Mixer", "KitchenAid", "Kitchen Appliances", 45000));
        productsDB.put("10", new Product("10", "Fitbit Charge 5", "Fitbit", "Wearable Tech", 50000));
    }

    public static InMemoryProductsDataAccess getInstance() {
        if (productInMemoryDataAccessInstance == null){
            productInMemoryDataAccessInstance = new InMemoryProductsDataAccess();
        }
        return productInMemoryDataAccessInstance;
    }


    @Override
    public Product getProductBySerialNumber(String serialNumber){
        return productsDB.get(serialNumber);
    }

    @Override
    public Collection<Product> getProductsList() {
        return productsDB.values();
    }

    @Override
    public Product getProductByName(String productName){
        for (Product product: productsDB.values()) {
            if(product.getName().equals(productName)){
                return product;
            }
        }
        return null;
    }
}
