package com.example.restaurant.ui.general;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "home")
@PageTitle("Home")
public class HomeView extends VerticalLayout {
Image welcome = new Image("images/welcome.png", "welcome");
    public HomeView() {
        add (
                welcome
        );
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.START);
        setSizeFull();
    }
}
