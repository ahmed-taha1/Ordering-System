package OrderingSystem.Products.DataAccess;

import OrderingSystem.Products.Entities.Product;

import java.util.*;
import java.util.stream.Collectors;

class InventoryItem{
    private final Product product;
    private final int count;
    public InventoryItem(Product product,int count){
        this.product = product;
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public Product getProduct() {
        return product;
    }
}
public class InMemoryProductsDataAccess implements IProductsDataAccess {
    Map<String, InventoryItem> productsDB;
    static InMemoryProductsDataAccess productInMemoryDataAccessInstance = null;

    private InMemoryProductsDataAccess(){
        productsDB = new HashMap<>();
        productsDB.put("1", new InventoryItem(new Product("1", "Samsung Galaxy S21", "Samsung", "Smartphones", 5000), 55000));
        productsDB.put("2", new InventoryItem(new Product("2", "Apple MacBook Pro", "Apple", "Laptops", 10000), 5900));
        productsDB.put("3", new InventoryItem(new Product("3", "Sony PlayStation 5", "Sony", "Gaming Consoles", 15000), 5200));
        productsDB.put("4", new InventoryItem(new Product("4", "Bose QuietComfort 35 II", "Bose", "Headphones", 20000), 6700));
        productsDB.put("5", new InventoryItem(new Product("5", "LG OLED C1 Series TV", "LG", "Televisions", 25000), 12000));
        productsDB.put("6", new InventoryItem(new Product("6", "Dell XPS 13", "Dell", "Laptops", 30000), 9000));
        productsDB.put("7", new InventoryItem(new Product("7", "Canon EOS R5", "Canon", "Cameras", 35000), 8730));
        productsDB.put("8", new InventoryItem(new Product("8", "Nike Air Zoom Pegasus", "Nike", "Shoes", 40000), 2990));
        productsDB.put("9", new InventoryItem(new Product("9", "KitchenAid Stand Mixer", "KitchenAid", "Kitchen Appliances", 45000), 5000));
        productsDB.put("10", new InventoryItem(new Product("10", "Fitbit Charge 5", "Fitbit", "Wearable Tech", 50000), 4887));
    }

    public static InMemoryProductsDataAccess getInstance() {
        if (productInMemoryDataAccessInstance == null){
            productInMemoryDataAccessInstance = new InMemoryProductsDataAccess();
        }
        return productInMemoryDataAccessInstance;
    }


    @Override
    public Product getProductBySerialNumber(String serialNumber){
        if(productsDB.get(serialNumber) == null)
            return null;
        return productsDB.get(serialNumber).getProduct();
    }

    @Override
    public Collection<Product> getProductsList() {
        return productsDB.values().stream()
                .map(InventoryItem::getProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> getCategoriesCount() {
        Map<String, Integer> categoriesCount = new HashMap<>();
        for (InventoryItem item : productsDB.values()) {
            categoriesCount.merge(item.getProduct().getCategory(), item.getCount(), Integer::sum);
        }
        return categoriesCount;
    }
}