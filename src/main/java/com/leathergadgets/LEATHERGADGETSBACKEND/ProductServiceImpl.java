package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductDTO> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId).stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getProductName(),
                        product.getMainImage(), // Ensures base64 string or image URL
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Product editProduct(Long id, Product updatedProduct) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product existing = optional.get();
            existing.setProductName(updatedProduct.getProductName());
            existing.setShortDescription(updatedProduct.getShortDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setDiscountPrice(updatedProduct.getDiscountPrice());
            existing.setQuantity(updatedProduct.getQuantity());
            existing.setMaterialType(updatedProduct.getMaterialType());
            existing.setProductType(updatedProduct.getProductType());
            existing.setColor(updatedProduct.getColor());
            existing.setWeight(updatedProduct.getWeight());
            existing.setSizes(updatedProduct.getSizes());
            existing.setSubImages(updatedProduct.getSubImages());
            existing.setMainImage(updatedProduct.getMainImage());
            existing.setSeller(updatedProduct.getSeller());
            return productRepository.save(existing);
        }
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
