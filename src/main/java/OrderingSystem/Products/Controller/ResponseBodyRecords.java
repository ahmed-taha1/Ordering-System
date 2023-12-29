package OrderingSystem.Products.Controller;

import OrderingSystem.Products.Entities.Product;

import java.util.Collection;
import java.util.Map;

public class ResponseBodyRecords {
    public record getProductsResponseBody(
            Map<String, Integer> categoryCount,
            Collection<Product> products
    ){}
}
