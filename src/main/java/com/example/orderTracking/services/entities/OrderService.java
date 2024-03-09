package com.example.orderTracking.services.entities;

import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.OrderRepository;
import com.example.orderTracking.requests.entityRequests.Order.OrderRequest;
import com.example.orderTracking.requests.entityRequests.Order.converters.OrderRequestToOrder;
import com.example.orderTracking.responses.entityResponses.Order.OrderResponse;
import com.example.orderTracking.responses.entityResponses.Order.converters.OrderToOrderResponse;
import com.example.orderTracking.responses.entityResponses.Product.converters.ProductToProductResponse;
import com.example.orderTracking.responses.nestedResponses.product.converters.ProductToOrderProductResponse;
import com.example.orderTracking.responses.nestedResponses.user.converters.UserToOrderUserResponse;
import com.example.orderTracking.services.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        User user;
        Product product;
        try {
            user = userService.getUserById(orderRequest.getUserId());
            product = productService.getProductById(orderRequest.getProductId());
        } catch (Exception e) {
            throw new RuntimeException("Error while creating order: " + e.getMessage());
        }
        Order order = OrderRequestToOrder.convert(orderRequest, user, product);

        if (!checkIfProductIsAvailable(product, order.getQuantity())) {
            throw new RuntimeException("Product is not available");
        }

        if (!checkIfUserHasACardInfo(user)) {
            throw new RuntimeException("User does not have a card info");
        }
        orderRepository.save(order);
        productService.reduceQuantity(product, order.getQuantity());
        return OrderToOrderResponse.convert(order,
                UserToOrderUserResponse.convert(user),
                ProductToOrderProductResponse.convert(product));
    }

    public boolean checkIfProductIsAvailable(Product product, Integer quantity) {
        return product.getQuantity() >= quantity;
    }

    public boolean checkIfUserHasACardInfo(User user) {
        return user.getCardInfo() != null;
    }







}
