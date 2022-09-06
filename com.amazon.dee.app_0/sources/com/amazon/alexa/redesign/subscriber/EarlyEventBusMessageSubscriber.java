package com.amazon.alexa.redesign.subscriber;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.redesign.presenter.EarlyEventBusMessageHandler;
import com.amazon.alexa.redesign.repository.EarlyEventBusMessageRepository;
/* loaded from: classes10.dex */
public class EarlyEventBusMessageSubscriber {
    private final EventBus eventBus;
    private EarlyEventBusMessageHandler messageHandler;
    private Subscriber.SubscriberUuid subscriberUuid;

    public EarlyEventBusMessageSubscriber(EventBus eventBus, EarlyEventBusMessageRepository earlyEventBusMessageRepository) {
        this.eventBus = eventBus;
        initSubscriber();
        this.messageHandler = new EarlyEventBusMessageHandler(earlyEventBusMessageRepository);
    }

    private void initSubscriber() {
        MultiFilterSubscriber newSubscriber = this.eventBus.getNewSubscriber();
        this.subscriberUuid = newSubscriber.getSubscriberUuid();
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(Constants.HOME_CHANNEL_EVENT_ADD_CARD), new MessageHandler() { // from class: com.amazon.alexa.redesign.subscriber.-$$Lambda$EarlyEventBusMessageSubscriber$KF3m9IffhcdQpqh1yTpUbhGQaO0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EarlyEventBusMessageSubscriber.this.lambda$initSubscriber$0$EarlyEventBusMessageSubscriber(message);
            }
        });
    }

    public /* synthetic */ void lambda$initSubscriber$0$EarlyEventBusMessageSubscriber(Message message) {
        this.messageHandler.handleMessage(message);
    }

    public void unsubscribe() {
        this.eventBus.unsubscribe(this.subscriberUuid);
    }
}
