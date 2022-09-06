package com.amazon.alexa.mode.util;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
/* loaded from: classes9.dex */
public class EventHelper {
    private EventBus eventBus;

    public EventHelper(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void publishEvent(String str) {
        publishEvent(str, "");
    }

    public void publishEvent(String str, String str2) {
        Message build = new Message.Builder().setEventType(str).setPayload(str2).build();
        EventBus eventBus = this.eventBus;
        if (eventBus != null) {
            eventBus.publish(build);
        }
    }
}
