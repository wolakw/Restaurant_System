package com.example.restaurant.backend.service;

import com.example.restaurant.backend.entity.Delivery;
import com.example.restaurant.backend.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DeliveryService implements CrudListener<Delivery> {
    private final DeliveryRepository repository;

    @Override
    public Collection<Delivery> findAll() {
        return repository.findAll();
    }

    @Override
    public Delivery add(Delivery delivery) {
        return repository.save(delivery);
    }

    @Override
    public Delivery update(Delivery delivery) {
        return repository.save(delivery);
    }

    @Override
    public void delete(Delivery delivery) {
        repository.delete(delivery);
    }
}
