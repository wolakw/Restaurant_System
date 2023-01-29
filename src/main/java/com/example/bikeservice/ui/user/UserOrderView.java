package com.example.bikeservice.ui.user;

import com.example.bikeservice.backend.entity.CustomerOrder;
import com.example.bikeservice.backend.service.OrderService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "userorderview")
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
