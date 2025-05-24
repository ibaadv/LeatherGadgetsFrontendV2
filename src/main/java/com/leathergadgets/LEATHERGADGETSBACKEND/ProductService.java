package com.leathergadgets.LEATHERGADGETSBACKEND;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);

    List<ProductDTO> getProductsBySellerId(Long sellerId);

    // ✅ New methods
    Product editProduct(Long id, Product updatedProduct);
    boolean deleteProduct(Long id);
}