package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/seller/{sellerId}")
    public List<ProductDTO> getProductsBySellerId(@PathVariable Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    @PutMapping("/{id}")
    public Product editProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.editProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
