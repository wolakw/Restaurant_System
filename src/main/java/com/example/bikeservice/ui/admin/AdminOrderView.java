package com.example.bikeservice.ui.admin;

import com.example.bikeservice.backend.entity.CustomerOrder;
import com.example.bikeservice.backend.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route(value = "adminorder")
@PageTitle("Orders")
public class AdminOrderView extends VerticalLayout {
    public AdminOrderView(OrderService service) {
        var crud = new GridCrud<>(CustomerOrder.class, service);
        crud.setAddOperationVisible(false);
        crud.getGrid().setColumns("id", "name","description", "client", "employee", "orderDate", "pickupDate", "delivery", "status");
        crud.getCrudFormFactory().setVisibleProperties("name","description", "client", "employee", "pickupDate", "delivery", "status");
        add(crud);
    }
}