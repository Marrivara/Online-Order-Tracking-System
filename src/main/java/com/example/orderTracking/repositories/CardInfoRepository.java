package com.example.orderTracking.repositories;

import com.example.orderTracking.model.entities.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo,Integer> {
}
