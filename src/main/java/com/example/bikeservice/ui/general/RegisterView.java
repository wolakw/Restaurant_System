package com.example.bikeservice.ui.general;

import com.example.bikeservice.backend.repository.UserRepository;
import com.example.bikeservice.backend.service.AuthService;
import com.example.bikeservice.backend.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterView extends VerticalLayout {
    private final AuthService authService;
    private final UserService service;
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField username = new TextField("Username");
    PasswordField password1 = new PasswordField("Password");
    PasswordField password2 = new PasswordField("Confirm Password");

    public RegisterView(AuthService authService, UserService userService) {
        this.authService = authService;
        this.service =  userService;
        add(
                new Image("images/logo1.png", "logo1"),
                new H1("Register"),
                new HorizontalLayout(firstName, lastName),
                new HorizontalLayout(password1, password2),
                username,
                new HorizontalLayout(new Button("Register", event -> register(
                        firstName.getValue(),
                        lastName.getValue(),
                        username.getValue(),
                        password1.getValue(),
                        password2.getValue()
                )),new Button("Log in", event -> {
                    UI.getCurrent().navigate("");
                }))
        );
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
    }

    private void setClear() {
        firstName.setValue("");
        lastName.setValue("");
        password1.setValue("");
        password2.setValue("");
        username.setValue("");
    }

    private void register(String firstname, String lastname, String username, String password1, String password2) {
        if (firstname.isEmpty()) {
            Notification.show("Enter first name");
        } else if (lastname.trim().isEmpty()) {
            Notification.show("Enter last name");
        } else if (password1.isEmpty()) {
            Notification.show("Enter password");
        } else if (!password1.equals(password2)) {
            Notification.show("Password don't match");
        } else if (username.trim().isEmpty()) {
            Notification.show("Enter username");
        } else if (service.findUser(username)) {
            Notification.show("Username isn't available");
        } else {
            authService.register(firstname, lastname, username, password1);
            setClear();
            Notification.show("Registration succeeded");
        }
    }
}