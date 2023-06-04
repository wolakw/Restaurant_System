package com.example.restaurant.ui.emp;

import com.example.restaurant.backend.entity.CustomerOrder;
import com.example.restaurant.backend.service.OrderService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Orders")
public class EmpOrderView extends VerticalLayout {

    public EmpOrderView(OrderService service) {
        var crud = new GridCrud<>(CustomerOrder.class);
        crud.setFindAllOperation(service::findAllEmployee);
        crud.setAddOperationVisible(false);
        crud.setDeleteOperationVisible(false);
        crud.getGrid().setColumns("id", "name","description", "client", "employee", "orderDate", "pickupDate", "delivery", "status");
        crud.getCrudFormFactory().setVisibleProperties("pickupDate", "status");
        add(crud);
    }
}
