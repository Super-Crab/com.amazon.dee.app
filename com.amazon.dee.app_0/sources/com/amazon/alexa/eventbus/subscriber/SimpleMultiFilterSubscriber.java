package com.amazon.alexa.eventbus.subscriber;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public class SimpleMultiFilterSubscriber implements MultiFilterSubscriber {
    @VisibleForTesting
    final Map<MultiFilterSubscriber.FilterUuid, FilterHandler> handlers = new ConcurrentHashMap();
    private final Subscriber.SubscriberUuid uuid = new SubscriberUuidImpl();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class FilterHandler {
        private final MessageFilter filter;
        private final MessageHandler handler;
        private final MultiFilterSubscriber.FilterUuid uuid = new FilterUuidImpl();

        FilterHandler(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler) {
            this.filter = messageFilter;
            this.handler = messageHandler;
        }

        MessageFilter getFilter() {
            return this.filter;
        }

        public MultiFilterSubscriber.FilterUuid getFilterUuid() {
            return this.uuid;
        }

        MessageHandler getHandler() {
            return this.handler;
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public Subscriber.SubscriberUuid getSubscriberUuid() {
        return this.uuid;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.uuid.getUuid();
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        ArrayList<MessageHandler> arrayList = new ArrayList();
        for (FilterHandler filterHandler : this.handlers.values()) {
            if (filterHandler.getFilter().isMatch(message)) {
                arrayList.add(filterHandler.getHandler());
            }
        }
        for (MessageHandler messageHandler : arrayList) {
            messageHandler.handle(message);
        }
    }

    @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
    @Deprecated
    public UUID subscribe(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler) {
        return subscribeFilter(messageFilter, messageHandler).getUuid();
    }

    @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
    public MultiFilterSubscriber.FilterUuid subscribeFilter(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler) {
        FilterHandler filterHandler = new FilterHandler(messageFilter, messageHandler);
        this.handlers.put(filterHandler.getFilterUuid(), filterHandler);
        return filterHandler.getFilterUuid();
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        for (FilterHandler filterHandler : this.handlers.values()) {
            if (filterHandler.getFilter().isMatch(message)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
    @Deprecated
    public void unsubscribe(@NonNull UUID uuid) {
        for (MultiFilterSubscriber.FilterUuid filterUuid : this.handlers.keySet()) {
            if (filterUuid.getUuid().equals(uuid)) {
                this.handlers.remove(filterUuid);
                return;
            }
        }
    }

    @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
    public boolean unsubscribeFilter(@NonNull MultiFilterSubscriber.FilterUuid filterUuid) {
        return this.handlers.remove(filterUuid) != null;
    }
}
