package com.example.bikeservice.ui.emp;

import com.example.bikeservice.backend.entity.CustomerOrder;
import com.example.bikeservice.backend.service.OrderService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "emporderview")
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
