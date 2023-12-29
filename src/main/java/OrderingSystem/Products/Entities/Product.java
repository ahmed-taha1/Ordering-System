package OrderingSystem.Products.Entities;

public class Product {
    private final String serialNumber;
    private final String name;
    private final String vendor;
    private final String category;
    private final double price;

    public Product(String serialNumber, String name, String vendor, String category, double price) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
