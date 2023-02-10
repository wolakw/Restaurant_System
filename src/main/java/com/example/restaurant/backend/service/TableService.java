package com.example.restaurant.backend.service;

import com.example.restaurant.backend.entity.RestaurantTable;
import com.example.restaurant.backend.entity.User;
import com.example.restaurant.backend.repository.TableRepository;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.ArrayList;
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

    public void book(RestaurantTable table, String user) {
        table.setReserved(true);
        table.setReservedBy(user);
        repository.save(table);
    }

    public void cancel(RestaurantTable table) {
        table.setReserved(false);
        table.setReservedBy(null);
        repository.save(table);
    }

    public RestaurantTable findById(Long id) {
        RestaurantTable table = null;
        Collection<RestaurantTable> tables = findAll();
        for (RestaurantTable t : tables) {
            if(t.getId().equals(id)) {
                table = t;
            }
        }
        return table;
    }

    public Collection<RestaurantTable> findAllAvailable() {
        Collection<RestaurantTable> tables = findAll();
        Collection<RestaurantTable> tables2 = new ArrayList<>();
        for (RestaurantTable t : tables) {
            if(!t.isReserved()) {
                tables2.add(t);
            }
        }
        return tables2;
    }

    public Collection<RestaurantTable> findAllBooked() {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        Collection<RestaurantTable> tables = findAll();
        Collection<RestaurantTable> tables2 = new ArrayList<>();
        for (RestaurantTable t : tables) {
            if(user.getUsername().equals(t.getReservedBy())) {
                tables2.add(t);
            }
        }
        return tables2;
    }
}

