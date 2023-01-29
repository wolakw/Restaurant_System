package com.example.bikeservice.ui.admin;

import com.example.bikeservice.backend.entity.Role;
import com.example.bikeservice.backend.entity.User;
import com.example.bikeservice.backend.repository.UserRepository;
import com.example.bikeservice.backend.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "adminusers")
@PageTitle("Users")
public class AdminUserView extends VerticalLayout {

    private final UserService userService;
    private final UserRepository userRepository;
    private TextField username = new TextField("Username");

    public AdminUserView(UserService service, UserRepository userRepository) {
        this.userService = service;
        this.userRepository = userRepository;
        var crud = new GridCrud<>(User.class);
        crud.setFindAllOperation(service::findAll);
        crud.setAddOperationVisible(false);
        crud.getGrid().setColumns("id", "firstName", "lastName", "username", "role", "passwordHash");
        crud.getCrudFormFactory().setVisibleProperties("firstName", "lastName", "username", "role");
        add(
                crud,
                new H1("Add new employee"),
                username,
                new Button("Add", event -> addEmp(
                        username.getValue()
                ))
        );
    }

    public void addEmp(String username1) {
        User user = userRepository.getByUsername(username1);
        if (user == null) {
            Notification.show("User doesn't exist");
        } else {
            user.setRole(Role.EMP);
            userRepository.save(user);
            Notification.show("User converted to employee");
            username.setValue("");
        }
    }
}
