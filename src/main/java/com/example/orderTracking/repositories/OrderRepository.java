package com.example.orderTracking.repositories;

import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findAllByUser(User user);
}
