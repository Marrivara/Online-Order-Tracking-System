package com.example.orderTracking.controllers.entities;


import com.example.orderTracking.requests.entityRequests.CardInfo.CardInfoRequest;
import com.example.orderTracking.responses.entityResponses.CardInfo.CardInfoResponse;
import com.example.orderTracking.services.entities.CardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardInfo")
@RequiredArgsConstructor
public class CardInfoController {

    private final CardInfoService cardInfoService;


    @PostMapping("/create")
    public ResponseEntity<CardInfoResponse> createCardInfo(@RequestBody CardInfoRequest cardInfoRequest){
        return ResponseEntity.ok(cardInfoService.createCardInfo(cardInfoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardInfoResponse> getCardInfoById(@PathVariable Integer id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(cardInfoService.getCardInfoResponseById(id, token));
    }

    //@PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<CardInfoResponse>> getAllCardInfos(){
        return ResponseEntity.ok(cardInfoService.getAllCardInfos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardInfoResponse> deleteCardInfo(@PathVariable Integer id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(cardInfoService.deleteCardInfo(id, token));
    }

}