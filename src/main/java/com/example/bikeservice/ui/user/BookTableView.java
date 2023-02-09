package com.example.bikeservice.ui.user;

import com.example.bikeservice.backend.entity.RestaurantTable;
import com.example.bikeservice.backend.entity.User;
import com.example.bikeservice.backend.repository.TableRepository;
import com.example.bikeservice.backend.service.TableService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "booktableview")
@PageTitle("Book table")
public class BookTableView extends VerticalLayout {

    private final TableService tableService;
    private final TableRepository tableRepository;

    NumberField table = new NumberField("Table id");
    NumberField table2 = new NumberField("Table id");

    public BookTableView(TableService service, TableRepository tableRepository) {
        this.tableService = service;
        this.tableRepository = tableRepository;

        var tableCrud = new GridCrud<>(RestaurantTable.class, service);
        tableCrud.setFindAllOperation(service::findAllAvailable);
        tableCrud.setAddOperationVisible(false);
        tableCrud.setDeleteOperationVisible(false);
        tableCrud.setUpdateOperationVisible(false);

        var crud = new GridCrud<>(RestaurantTable.class, service);
        crud.setFindAllOperation(service::findAllBooked);
        crud.setAddOperationVisible(false);
        crud.setDeleteOperationVisible(false);
        crud.setUpdateOperationVisible(false);

        add(
                new H1("Available tables in the reastaurant"),
                tableCrud,
                new H1("Book table"),
                table,
                new Button("Book", event -> bookTable(table.getValue())),
                crud,
                new H1("Cancel reservation"),
                table2,
                new Button("Cancel", event -> cancelReservation(table2.getValue())));
    }

    public void bookTable(Double id) {
        if (table.isEmpty()) {
            Notification.show("Enter table id");
        } else {
            Long number = id.longValue();
            RestaurantTable restaurantTable = tableService.findById(number);
            if (restaurantTable == null) {
                Notification.show("Table doesn't exist");
            } else if (restaurantTable.isReserved()) {
                Notification.show("Table isn't available");
            } else {
                User user = VaadinSession.getCurrent().getAttribute(User.class);
                tableService.book(restaurantTable, user.getUsername());
                Notification.show("Table booked");
                table.setValue(null);
            }
        }
    }

    public void cancelReservation(Double id) {
        if (table.isEmpty()) {
            Notification.show("Enter table id");
        } else {

        }
    }
}
