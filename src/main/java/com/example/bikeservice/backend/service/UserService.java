package com.example.bikeservice.backend.service;

import com.example.bikeservice.backend.entity.Role;
import com.example.bikeservice.backend.entity.User;
import com.example.bikeservice.backend.repository.UserRepository;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements CrudListener<User> {

    private final UserRepository repository;

    @Override
    public Collection<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User add(User user) { return repository.save(user); }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    public void editData(User user, String firstname, String lastname, String username) {
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUsername(username);
        repository.save(user);
    }

    public Collection<User> findAllUsername () {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        String username = user.getUsername();
        Collection<User> users = findAll();
        Collection<User> users2 = new ArrayList<>();
        for (User u : users) {
            if(u.getUsername().equals(username)) {
                users2.add(u);
            }
        }
        return users2;
    }

    public boolean findUser(String username) {
        Collection<User> users = findAll();
        for (User u : users) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
