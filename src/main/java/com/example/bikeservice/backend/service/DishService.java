package com.example.bikeservice.backend.service;

import com.example.bikeservice.backend.entity.Dish;
import com.example.bikeservice.backend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DishService implements CrudListener<Dish> {
    private final DishRepository repository;

    @Override
    public Collection<Dish> findAll() {
        return repository.findAll();
    }

    @Override
    public Dish add(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public Dish update(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public void delete(Dish dish) {
        repository.delete(dish);
    }
}
