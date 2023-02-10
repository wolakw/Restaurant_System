package com.example.restaurant.ui.admin;

import com.example.restaurant.backend.entity.Delivery;
import com.example.restaurant.backend.entity.Dish;
import com.example.restaurant.backend.entity.RestaurantTable;
import com.example.restaurant.backend.service.DeliveryService;
import com.example.restaurant.backend.service.DishService;
import com.example.restaurant.backend.service.TableService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "admin")
@PageTitle("Admin")
public class AdminView extends VerticalLayout {
    public AdminView(DishService service, TableService tableService, DeliveryService deliveryService) {
        var crud = new GridCrud<>(Dish.class, service);
        crud.getCrudFormFactory().setVisibleProperties("name", "price");

        var tableCrud = new GridCrud<>(RestaurantTable.class, tableService);

        var deliverycrud = new GridCrud<>(Delivery.class, deliveryService);
        deliverycrud.getCrudFormFactory().setVisibleProperties("name");
        add(
                new H1("Dishes"),
                crud,
                new H1("Tables"),
                tableCrud,
                new H1("Delivery options"),
                deliverycrud);
    }
}