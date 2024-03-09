package com.example.orderTracking.model.baseModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date createdAt;

    private Date deletedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.ofOffset( "GMT", ZoneOffset.ofHours(0));
        this.createdAt = Date.from(localDateTime.atZone(zoneId).toInstant());
    }
}