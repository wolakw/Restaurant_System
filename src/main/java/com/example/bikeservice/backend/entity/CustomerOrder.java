package com.example.bikeservice.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class CustomerOrder {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Delivery delivery;

    private Status status;

    private String client;

    private String employee;

    private LocalDate orderDate;
    private LocalDate pickupDate;

    public CustomerOrder(String name, String description, Delivery delivery, Status status, String client) {
        this.name = name;
        this.description = description;
        this.delivery = delivery;
        this.status = status;
        this.client = client;
        this.employee = "Not assigned";
        this.orderDate = LocalDate.now();
        this.pickupDate = LocalDate.now().plusDays(5);
    }

    public CustomerOrder(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
