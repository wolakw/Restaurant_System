package com.example.bikeservice.backend.service;

import com.example.bikeservice.backend.entity.RestaurantTable;
import com.example.bikeservice.backend.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TableService implements CrudListener<RestaurantTable> {

    private final TableRepository repository;

    @Override
    public Collection<RestaurantTable> findAll() {
        return repository.findAll();
    }

    @Override
    public RestaurantTable add(RestaurantTable restaurantTable) {
        return repository.save(restaurantTable);
    }

    @Override
    public RestaurantTable update(RestaurantTable restaurantTable) {
        return repository.save(restaurantTable);
    }

    @Override
    public void delete(RestaurantTable restaurantTable) {
        repository.delete(restaurantTable);
    }
}

