package com.example.restaurant.ui.user;

import com.example.restaurant.backend.entity.User;
import com.example.restaurant.backend.service.AuthService;
import com.example.restaurant.backend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

@PageTitle("Settings")
public class SettingsView extends VerticalLayout {
    private final UserService userService;
    private final AuthService authService;
    private User user = VaadinSession.getCurrent().getAttribute(User.class);
    private TextField firstName = new TextField("Fisrt name");
    private TextField lastName = new TextField("Last name");
    private TextField username = new TextField("Username");
    private PasswordField oldPassword = new PasswordField("Old Password");
    private PasswordField newPassword1 = new PasswordField("New Password");
    private PasswordField newPassword2 = new PasswordField(" Confirm password");
    private PasswordField delete = new PasswordField("Password");


    public SettingsView(UserService service, AuthService authService) {
        this.userService = service;
        this.authService = authService;
        firstName.setValue(user.getFirstName());
        lastName.setValue(user.getLastName());
        username.setValue(user.getUsername());
        add(
                new H1("Change your data"),
                new HorizontalLayout(firstName, oldPassword),
                new HorizontalLayout(lastName, newPassword1),
                new HorizontalLayout(username, newPassword2),
                new HorizontalLayout(new Button("Edit data", event -> editData(
                        firstName.getValue(),
                        lastName.getValue(),
                        username.getValue()
                )), new Button("Change password", event -> changePassword(
                        oldPassword.getValue(),
                        newPassword1.getValue(),
                        newPassword2.getValue()
                ))),
                new H1("Delete your account"),
                delete,
                new Button("Delete account", event -> deleteAcc(
                        delete.getValue()
                ))
        );
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.START);
        setSizeFull();
    }

    public void editData(String firstname, String lastname, String username) {
        if (userService.findUser(username)) {
            Notification.show("Username isn't available");
        } else {
            userService.editData(user, firstname, lastname, username);
            Notification.show("Data changed");
        }
    }
    public void changePassword(String oldpassword, String newpassword1, String newpassword2) {
        if (!user.checkPassword(oldpassword)) {
            Notification.show("Old password isn't correct");
        } else if (!newpassword1.equals(newpassword2) || newpassword2.isEmpty()) {
            Notification.show("New passwords don't match");
        } else {
            authService.changePassword(user,newpassword1);
            Notification.show("Password changed");
            setClear();
        }
    }

    public void deleteAcc(String password) {
        if (!user.checkPassword(password)) {
            Notification.show("Password isn't correct");
        } else {
            UI.getCurrent().getPage().setLocation("login");
            userService.delete(user);
            VaadinSession.getCurrent().getSession().invalidate();
            VaadinSession.getCurrent().close();
            Notification.show("Account deleted");
        }
    }

    private void setClear() {
        oldPassword.setValue("");
        newPassword1.setValue("");
        newPassword2.setValue("");
    }
}