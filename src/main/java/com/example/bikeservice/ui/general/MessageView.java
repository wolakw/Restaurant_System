package com.example.bikeservice.ui.general;

import com.example.bikeservice.backend.entity.User;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Route(value = "messages")
@PageTitle("Messages")
public class MessageView extends VerticalLayout {

    MessageList list = new MessageList();
    MessageListItem message;

    List<MessageListItem> messageList = new ArrayList<>();
    private User user = VaadinSession.getCurrent().getAttribute(User.class);
    TextField input = new TextField("Type in your message");
    HorizontalLayout layout = new HorizontalLayout();
    MessageView() {
        add(
                list,
                new HorizontalLayout(input, new Button("Send", event -> sendMessage(
                        input.getValue()
                )))
        );
        setAlignItems(Alignment.STRETCH);
        setJustifyContentMode(JustifyContentMode.END);
        setSizeFull();
    }

    public void sendMessage(String message1) {
        message = new MessageListItem(message1, LocalDateTime.now().toInstant(ZoneOffset.UTC), user.getFirstName() + " " + user.getLastName());
        messageList.add(message);
        list.setItems(messageList);
        input.setValue("");
    }
}
