package com.example.orderTracking.services.entities;

import com.example.orderTracking.enums.OrderStatus;
import com.example.orderTracking.enums.ProductCategory;
import com.example.orderTracking.exceptions.runtimeExceptions.NotSuitableException.UserDoesNotHaveACardInfoException;
import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.OrderRepository;
import com.example.orderTracking.requests.entityRequests.Order.OrderRequest;
import com.example.orderTracking.requests.entityRequests.Order.converters.OrderRequestToOrder;
import com.example.orderTracking.services.jwt.JwtService;
import com.example.orderTracking.services.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;
    private UserService userService;
    private ProductService productService;
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        userService = Mockito.mock(UserService.class);
        productService = Mockito.mock(ProductService.class);
        jwtService = Mockito.mock(JwtService.class);
        orderService = new OrderService(orderRepository, userService, productService, jwtService);
    }

    @Test
    public void testCreateOrder_whenRequestIsValid_shouldReturnOrderResponse() {
    }

    @Test
    public void testCreateOrder_whenUserIsNull_shouldThrowRuntimeException() {
    }


    @Test
    public void testCreateOrder_whenProductIsNull_shouldThrowRuntimeException() {
    }

    @Test
    public void testCreateOrder_whenProductQuantityIsLessThanAvailableQuantity_shouldThrowProductQuantityIsNotEnoughException() {
    }

    @Test
    public void testCreateOrder_whenUserDoesNotHaveCardInfo_shouldThrowUserDoesNotHaveCardInfoException() {
//        User user = new User();
//        Product product = new Product("Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS);
//        OrderRequest orderRequest = new OrderRequest(1, 1, 5);
//        Order order = new Order(user, product, 5, 50.0, OrderStatus.NEW);
//
//        Mockito.when(userService.getUserById(1)).thenReturn(user);
//        Mockito.when(productService.getProductById(1)).thenReturn(product);
//
//        try(MockedStatic<OrderRequestToOrder> converter = Mockito.mockStatic(OrderRequestToOrder.class)) {
//            converter.when(() -> OrderRequestToOrder.convert(orderRequest, user, product)).thenReturn(order);
//            Mockito.when(orderService.checkIfProductIsAvailable(product, 5)).thenReturn(true);
//            Mockito.when(orderService.checkIfUserHasACardInfo(user)).thenReturn(false);
//
//            assertThrows(UserDoesNotHaveACardInfoException.class, () -> orderService.createOrder(orderRequest));
//        }


    }

}