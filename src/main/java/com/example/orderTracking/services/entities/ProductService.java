package com.example.orderTracking.services.entities;

import com.example.orderTracking.enums.ProductCategory;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.repositories.ProductRepository;
import com.example.orderTracking.requests.entityRequests.Product.ProductRequest;
import com.example.orderTracking.requests.entityRequests.Product.ProductSpecRequest;
import com.example.orderTracking.requests.entityRequests.Product.converters.ProductRequestToProduct;
import com.example.orderTracking.responses.entityResponses.Product.ProductResponse;
import com.example.orderTracking.responses.entityResponses.Product.converters.ProductToProductResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse getProductResponseById(Integer id) {
        return ProductToProductResponse.convert(getProductById(id));
    }


    public ProductResponse createProductType(ProductRequest productRequest) {
        Product product = ProductRequestToProduct.convert(productRequest);
        return ProductToProductResponse.convert(productRepository.save(product));
    }

    public List<ProductResponse> getAllProductTypes() {
        return ProductToProductResponse.convertMultiple(productRepository.findAll());
    }

    public ProductResponse updateProductQuantity(Integer id, Integer quantity) {
        Product product = getProductById(id);
        product.setQuantity(quantity);
        return ProductToProductResponse.convert(productRepository.save(product));
    }

    public void reduceQuantity(Product product, Integer quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    public void increaseQuantity(Product product, Integer quantity) {
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProductsWithFilters(ProductSpecRequest productSpecRequest) {

        return ProductToProductResponse.convertMultiple(productRepository.findAll(filterProduct(productSpecRequest)));
    }

    private Specification<Product> filterProduct(ProductSpecRequest productSpecRequest) {
        List<Specification<Product>> specs = new ArrayList<>();
        if (productSpecRequest.getName() != null) {
            Specification<Product> nameSpec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), "%" + productSpecRequest.getName() + "%");
            specs.add(nameSpec);
        }

        if (productSpecRequest.getCategory()!= null) {
            Specification<Product> categorySpec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category"), productSpecRequest.getCategory());
            specs.add(categorySpec);
        }

        if (productSpecRequest.getBottomPrice() != null) {
            Specification<Product> bottomPriceSpec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("price"), productSpecRequest.getBottomPrice());
            specs.add(bottomPriceSpec);
        }

        if (productSpecRequest.getTopPrice() != null) {
            Specification<Product> topPriceSpec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("price"), productSpecRequest.getTopPrice());
            specs.add(topPriceSpec);
        }

        if (specs.isEmpty()) {
            return null;
        }
        Specification<Product> combinedSpec = Specification.where(specs.get(0));
        for (int i = 1; i < specs.size(); i++) {
            combinedSpec = combinedSpec.and(specs.get(i));
        }

        return combinedSpec;
    }

}
