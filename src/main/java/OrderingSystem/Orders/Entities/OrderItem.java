package OrderingSystem.Orders.Entities;

import OrderingSystem.Products.Product;

public class OrderItem {
    private final Product product;
    private final int count;

    public OrderItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }
    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }
    public double getItemPrice(){
        return product.getPrice() * count;
    }
}
