package com.example.bikeservice.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class RestaurantTable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isReserved;

    private String reservedBy;

    private int seats;

    public RestaurantTable(boolean isReserved, String reservedBy, int seats) {
        this.isReserved = isReserved;
        this.reservedBy = reservedBy;
        this.seats = seats;
    }

    public RestaurantTable(boolean isReserved, String reservedBy) {
        this.isReserved = isReserved;
        this.reservedBy = reservedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }
}
