package com.example.bikeservice.backend.service;

import com.example.bikeservice.ui.general.HomeView;
import com.example.bikeservice.ui.MainView;
import com.example.bikeservice.backend.entity.Role;
import com.example.bikeservice.backend.entity.User;
import com.example.bikeservice.backend.repository.UserRepository;
import com.example.bikeservice.ui.general.LogoutView;
import com.example.bikeservice.ui.admin.AdminUserView;
import com.example.bikeservice.ui.admin.AdminOrderView;
import com.example.bikeservice.ui.admin.AdminView;
import com.example.bikeservice.ui.emp.EmpOrderView;
import com.example.bikeservice.ui.user.BookTableView;
import com.example.bikeservice.ui.user.SettingsView;
import com.example.bikeservice.ui.user.UserOrderView;
import com.example.bikeservice.ui.user.MakeOrderView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import javax.swing.text.TableView;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {
    }

    public class AuthException extends Exception {
    }

    private final UserRepository userRepository;

    public AuthService(UserRepository clientRepository) {
        this.userRepository = clientRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        User user = userRepository.getByUsername(username);
        if (user != null && user.checkPassword(password)) {
            VaadinSession.getCurrent().setAttribute(User.class, user);
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(route.route, route.view, MainView.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();

        if (role.equals(Role.ADMIN)) {
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("adminusers", "Users", AdminUserView.class));
            routes.add(new AuthorizedRoute("adminorder", "Orders", AdminOrderView.class));
            routes.add(new AuthorizedRoute("admin", "Admin", AdminView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
        }
        if (role.equals(Role.EMP)) {
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("emporderview", "Orders", EmpOrderView.class));
            routes.add(new AuthorizedRoute("settings", "Settings", SettingsView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
        }
        if (role.equals(Role.USER)) {
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("makeorderview", "Make order", MakeOrderView.class));
            routes.add(new AuthorizedRoute("userorderview", "Orders", UserOrderView.class));
            routes.add(new AuthorizedRoute("booktableview", "Tables", BookTableView.class));
            routes.add(new AuthorizedRoute("settings", "Settings", SettingsView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
        }
        return routes;
    }

    public void register(String firstname, String lastname, String username, String password) {
        userRepository.save(new User(firstname, lastname, username, password, Role.USER));
    }

    public void changePassword(User user, String password) {
        user.setNewPassword(password);
        userRepository.save(user);
    }
}