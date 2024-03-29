package com.example.orderTracking.controllers.entities;

import com.example.orderTracking.requests.entityRequests.Order.ChangeOrderRequest;
import com.example.orderTracking.requests.entityRequests.Order.OrderRequest;
import com.example.orderTracking.responses.entityResponses.Order.OrderResponse;
import com.example.orderTracking.services.entities.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @PatchMapping("/change-status")
    public ResponseEntity<OrderResponse> changeOrderStatus(@RequestBody ChangeOrderRequest changeOrderRequest,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(orderService.changeOrderStatus(changeOrderRequest, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(orderService.getOrderResponseById(id, token));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(orderService.getAllOrders(token));
    }


}
