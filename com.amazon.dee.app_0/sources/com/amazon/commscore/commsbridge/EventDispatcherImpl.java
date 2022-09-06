package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import com.amazon.commscore.api.commsbridge.EventListener;
/* loaded from: classes12.dex */
class EventDispatcherImpl implements EventDispatcher {
    private final EventRegistry mRegistry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventDispatcherImpl(@NonNull EventRegistry eventRegistry) {
        this.mRegistry = eventRegistry;
    }

    @Override // com.amazon.commscore.commsbridge.EventDispatcher
    public void dispatch(@NonNull EventMessage eventMessage) {
        String name = eventMessage.getName();
        String obj = eventMessage.getPayload() != null ? eventMessage.getPayload().toString() : null;
        for (EventListener<String> eventListener : this.mRegistry.getEventListeners(name)) {
            eventListener.onEvent(obj);
        }
    }
}
