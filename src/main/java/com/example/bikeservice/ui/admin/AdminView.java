package com.example.bikeservice.ui.admin;

import com.example.bikeservice.backend.entity.Delivery;
import com.example.bikeservice.backend.entity.Job;
import com.example.bikeservice.backend.service.DeliveryService;
import com.example.bikeservice.backend.service.JobService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "admin")
@PageTitle("Admin")
public class AdminView extends VerticalLayout {
    public AdminView(JobService service, DeliveryService deliveryService) {
        var crud = new GridCrud<>(Job.class, service);
        crud.getCrudFormFactory().setVisibleProperties("name", "price");

        var deliverycrud = new GridCrud<>(Delivery.class, deliveryService);
        deliverycrud.getCrudFormFactory().setVisibleProperties("name");
        add(
                new H1("Products"),
                crud,
                new H1("Delivery options"),
                deliverycrud);
    }
}