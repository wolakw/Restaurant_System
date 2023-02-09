package com.example.bikeservice.ui.user;

import com.example.bikeservice.backend.entity.Delivery;
import com.example.bikeservice.backend.entity.Dish;
import com.example.bikeservice.backend.entity.RestaurantTable;
import com.example.bikeservice.backend.entity.User;
import com.example.bikeservice.backend.repository.DeliveryRepository;
import com.example.bikeservice.backend.repository.DishRepository;
import com.example.bikeservice.backend.repository.UserRepository;
import com.example.bikeservice.backend.service.OrderService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import java.util.List;

@Route(value = "makeorderview")
@PageTitle("Make order")
public class MakeOrderView extends VerticalLayout {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final DishRepository dishRepository;

    ComboBox<Dish> dish = new ComboBox<>("Dish");
    TextField description = new TextField("Description");

    ComboBox<Delivery> delivery = new ComboBox<>("Delivery type");

    public MakeOrderView(OrderService service, UserRepository userRepository, DeliveryRepository deliveryRepository, DishRepository dishRepository) {
        this.orderService = service;
        this.userRepository = userRepository;
        this.deliveryRepository = deliveryRepository;
        this.dishRepository = dishRepository;
        List<Delivery> deliveries = deliveryRepository.findAll();
        delivery.setItems(deliveries);
        delivery.setItemLabelGenerator(Delivery::getName);

        List<Dish> dishes = dishRepository.findAll();
        dish.setItems(dishes);
        dish.setItemLabelGenerator(Dish::getName);
        add (
                new H1("Place an order"),
                dish,
                description,
                delivery,
                new Button("Order", event -> makeOrder(
                        dish.getValue(),
                        description.getValue(),
                        delivery.getValue()
                ))
        );
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.START);
        setSizeFull();
    }

    public void makeOrder(Dish name, String description, Delivery delivery1) {
        if (dish.isEmpty()) {
            Notification.show("Choose dish");
        } else if(delivery.isEmpty()) {
            Notification.show("Choose delivery type");
        } else {
            User user = VaadinSession.getCurrent().getAttribute(User.class);
            orderService.newOrder(name, description, delivery1, user.getUsername());
            setClear();
            Notification.show("Order placed");
        }
    }

    private void setClear() {
        description.setValue("");
    }
}
