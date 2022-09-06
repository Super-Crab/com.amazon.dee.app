package com.amazon.matter.service;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.matter.eventbus.EventBusManager;
/* loaded from: classes9.dex */
public class MatterServiceImpl implements MatterService {
    private EventBusManager eventBusManager;

    public MatterServiceImpl(EventBus eventBus, Context context) {
        this.eventBusManager = new EventBusManager(eventBus, context);
    }

    @Override // com.amazon.matter.service.MatterService
    public void start() {
        this.eventBusManager.startListening();
    }

    @Override // com.amazon.matter.service.MatterService
    public void stop() {
        this.eventBusManager.stopListening();
    }
}
