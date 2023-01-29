package com.example.bikeservice.backend.service;

import com.example.bikeservice.backend.entity.CustomerOrder;
import com.example.bikeservice.backend.entity.Delivery;
import com.example.bikeservice.backend.entity.Status;
import com.example.bikeservice.backend.entity.User;
import com.example.bikeservice.backend.repository.OrderRepository;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrderService implements CrudListener<CustomerOrder> {
    private final OrderRepository repository;


    @Override
    public Collection<CustomerOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public CustomerOrder add(CustomerOrder order) { return repository.save(order); }

    @Override
    public CustomerOrder update(CustomerOrder order) {
        return repository.save(order);
    }

    @Override
    public void delete(CustomerOrder order) {
        repository.delete(order);
    }

    public void newOrder(String name, String description, Delivery delivery, String user) {
        repository.save(new CustomerOrder(name, description, delivery, Status.SUBMITTED, user));
    }

    public Collection<CustomerOrder> findAllEmployee() {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        String username = user.getUsername();
        Collection<CustomerOrder> orders = findAll();
        Collection<CustomerOrder> orders2 = new ArrayList<>();
        for (CustomerOrder o : orders) {
            if(o.getEmployee().equals(username)) {
                orders2.add(o);
            }
        }
        return orders2;
    }

    public Collection<CustomerOrder> findAllClients () {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        String username = user.getUsername();
        Collection<CustomerOrder> orders = findAll();
        Collection<CustomerOrder> orders2 = new ArrayList<>();
        for (CustomerOrder o : orders) {
            if(o.getClient().equals(username)) {
                orders2.add(o);
            }
        }
        return orders2;
    }
}
