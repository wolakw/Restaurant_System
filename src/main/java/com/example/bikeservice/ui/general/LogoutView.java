package com.example.bikeservice.ui.general;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.data.domain.AfterDomainEventPublication;

@Route("logout")
@PageTitle("Logout")
public class LogoutView extends VerticalLayout {

    public LogoutView() {
        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }
}
