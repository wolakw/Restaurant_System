package com.example.restaurant.ui.user;

import com.example.restaurant.backend.entity.CustomerOrder;
import com.example.restaurant.backend.service.OrderService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Orders")
public class UserOrderView extends VerticalLayout {
    public UserOrderView(OrderService service) {
        var crud = new GridCrud<>(CustomerOrder.class);
        crud.setFindAllOperation(service::findAllClients);
        crud.setAddOperationVisible(false);
        crud.setDeleteOperationVisible(false);
        crud.setUpdateOperationVisible(false);
        crud.getGrid().setColumns("id", "name","description", "client", "employee", "orderDate", "pickupDate", "delivery", "status");
        add(crud);
    }
}
