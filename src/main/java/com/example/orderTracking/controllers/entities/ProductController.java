package com.example.orderTracking.controllers.entities;

import com.example.orderTracking.requests.entityRequests.Product.ProductRequest;
import com.example.orderTracking.responses.entityResponses.Product.ProductResponse;
import com.example.orderTracking.services.entities.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProductType(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProductType(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductTypeById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductResponseById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProductTypes() {
        return ResponseEntity.ok(productService.getAllProductTypes());
    }

    @PatchMapping("/update-quantity/{id}")
    public ResponseEntity<ProductResponse> updateProductQuantity(@PathVariable Integer id, @RequestParam Integer quantity){
        return ResponseEntity.ok(productService.updateProductQuantity(id, quantity));
    }
}
