package com.amazon.alexa.redesign.presenter;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.redesign.repository.EarlyEventBusMessageRepository;
/* loaded from: classes10.dex */
public class EarlyEventBusMessageHandler {
    private final EarlyEventBusMessageRepository repository;

    public EarlyEventBusMessageHandler(EarlyEventBusMessageRepository earlyEventBusMessageRepository) {
        this.repository = earlyEventBusMessageRepository;
    }

    public void handleMessage(Message message) {
        this.repository.add(message);
    }
}
