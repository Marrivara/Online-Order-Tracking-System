package com.example.orderTracking.services.entities;

import com.example.orderTracking.enums.OrderStatus;
import com.example.orderTracking.enums.Role;
import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.OrderRepository;
import com.example.orderTracking.requests.entityRequests.Order.ChangeOrderRequest;
import com.example.orderTracking.requests.entityRequests.Order.OrderRequest;
import com.example.orderTracking.requests.entityRequests.Order.converters.OrderRequestToOrder;
import com.example.orderTracking.responses.entityResponses.Order.OrderResponse;
import com.example.orderTracking.responses.entityResponses.Order.converters.OrderToOrderResponse;
import com.example.orderTracking.responses.entityResponses.Product.converters.ProductToProductResponse;
import com.example.orderTracking.responses.nestedResponses.product.converters.ProductToOrderProductResponse;
import com.example.orderTracking.responses.nestedResponses.user.converters.UserToOrderUserResponse;
import com.example.orderTracking.services.jwt.JwtService;
import com.example.orderTracking.services.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    private final JwtService jwtService;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService, JwtService jwtService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.jwtService = jwtService;
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
        return OrderResponseConverterCaller(order, user, product);
    }

    public boolean checkIfProductIsAvailable(Product product, Integer quantity) {
        return product.getQuantity() >= quantity && product.getQuantity() > 0;
    }

    public boolean checkIfUserHasACardInfo(User user) {
        return user.getCardInfo() != null;
    }


    public OrderResponse changeOrderStatus(ChangeOrderRequest changeOrderRequest, String token) {
        Order order = getOrderById(changeOrderRequest.getOrderId());
        String userEmail = jwtService.extractUserEmail(token.substring(7));
        User user = userService.getUserByEmail(userEmail);
        Role role = user.getRole();

        //Check if status change is allowed
        if (checkIfStatusChangeIsAllowed(role, order, changeOrderRequest, user)) {
            order.setStatus(changeOrderRequest.getStatus());
            //If order is cancelled, increase the quantity of the product
            if(changeOrderRequest.getStatus().equals(OrderStatus.CANCELLED)){
                productService.increaseQuantity(order.getProduct(), order.getQuantity());
            }
            orderRepository.save(order);
            return OrderResponseConverterCaller(order, order.getUser(), order.getProduct());
        } else {
            throw new RuntimeException("Status change is not allowed");
        }
    }

    private boolean checkIfStatusChangeIsAllowed(Role role, Order order, ChangeOrderRequest changeOrderRequest, User user) {
        //If order is already cancelled, no status change is allowed
        if(order.getStatus().equals(OrderStatus.CANCELLED)){
            return false;
            //Manager can change the status of any order
        }else if (role.equals(Role.ROLE_MANAGER)) {
            return true;
            //Customer can only change the status of his/her own orders
        } else if (role.equals(Role.ROLE_CUSTOMER) && order.getUser().getId().equals(user.getId())) {
            //Customer can only cancel the order
            return changeOrderRequest.getStatus().equals(OrderStatus.CANCELLED);
        } else {
            return false;
        }
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderResponse getOrderResponseById(Integer orderId, String token) {
        String email = jwtService.extractUserEmail(token.substring(7));
        User user = userService.getUserByEmail(email);
        Order order = getOrderById(orderId);
        if (user.getRole().equals(Role.ROLE_CUSTOMER) && !order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only get your own orders");
        }
        return OrderResponseConverterCaller(order, order.getUser(), order.getProduct());
    }
    public List<OrderResponse> getAllOrders(String token) {
        String email = jwtService.extractUserEmail(token.substring(7));
        User user = userService.getUserByEmail(email);
        if(user.getRole().equals(Role.ROLE_CUSTOMER)) {
            return OrderToOrderResponse.convertMultiple(getAllOrdersByUser(user));
        } else if (user.getRole().equals(Role.ROLE_MANAGER)) {
            return OrderToOrderResponse.convertMultiple(orderRepository.findAll());
        }
        return null;
    }

    private List<Order> getAllOrdersByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    private OrderResponse OrderResponseConverterCaller(Order order, User user, Product product) {
        return OrderToOrderResponse.convert(order,
                UserToOrderUserResponse.convert(user),
                ProductToOrderProductResponse.convert(product));
    }
}
