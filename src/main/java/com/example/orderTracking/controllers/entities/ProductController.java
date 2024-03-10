package com.example.orderTracking.controllers.entities;

import com.example.orderTracking.enums.ProductCategory;
import com.example.orderTracking.requests.entityRequests.Product.ProductRequest;
import com.example.orderTracking.requests.entityRequests.Product.ProductSpecRequest;
import com.example.orderTracking.responses.entityResponses.Product.ProductResponse;
import com.example.orderTracking.services.entities.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProductType(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProductType(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductTypeById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductResponseById(id));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PatchMapping("/update-quantity/{id}")
    public ResponseEntity<ProductResponse> updateProductQuantity(@PathVariable Integer id, @RequestParam Integer quantity){
        return ResponseEntity.ok(productService.updateProductQuantity(id, quantity));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> gelAllProducts(@RequestParam(required = false) String name,
                                                                           @RequestParam(required = false) ProductCategory category,
                                                                         @RequestParam(required = false) Double bottomPrice,
                                                                           @RequestParam(required = false) Double topPrice){
        return ResponseEntity.ok(productService.getAllProductsWithFilters(new ProductSpecRequest(name, category, bottomPrice, topPrice)));
    }
}
