package com.amazon.alexa.redesign.repository;

import com.amazon.alexa.eventbus.api.Message;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class EarlyEventBusMessageRepository {
    List<Message> messages = new ArrayList();

    public void add(Message message) {
        this.messages.add(message);
    }

    public void clear() {
        this.messages.clear();
    }

    public List<Message> getMessages() {
        return this.messages;
    }
}
