package com.amazon.alexa.voice.elements;

import com.amazon.alexa.api.AlexaCardRendererListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener;
import java.util.UUID;
/* loaded from: classes11.dex */
public class AlexaCardEventSender extends NoOpAlexaServiceConnectionListener implements AlexaCardRendererListener {
    private static final String CARD_RENDERED_EVENT = "vox::card_rendered";
    private static final String SHOULD_SEND_ALEXA_CARD = "vox::enable_alexa_card_event_request";
    private static final String TAG = "AlexaCardManager";
    private final AlexaCardAPI alexaCardAPI;
    private final AlexaServicesConnection alexaServicesConnection;
    private final EventBus eventBus;
    private boolean shouldSendAlexaCard;
    private UUID shouldSendAlexaCardSubscription;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaCardEventSender(AlexaServicesConnection alexaServicesConnection, AlexaCardAPI alexaCardAPI, EventBus eventBus) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.alexaCardAPI = alexaCardAPI;
        this.eventBus = eventBus;
    }

    public void initialize() {
        this.alexaServicesConnection.registerListener(this);
        this.shouldSendAlexaCardSubscription = this.eventBus.getSubscriber().subscribe($$Lambda$AlexaCardEventSender$BDkUa_c_ZVIK29_CAShlKLOBvgw.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.voice.elements.-$$Lambda$AlexaCardEventSender$-2vZFgzMPBmJBSpMTpsL9iXh_zk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaCardEventSender.this.lambda$initialize$1$AlexaCardEventSender(message);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$1$AlexaCardEventSender(Message message) {
        this.shouldSendAlexaCard = Boolean.parseBoolean(message.getPayloadAsString());
    }

    @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        this.alexaCardAPI.registerForAlexaCardRenderer(this.alexaServicesConnection, this);
    }

    @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        this.alexaCardAPI.deRegisterForAlexaCardRenderer(this.alexaServicesConnection, this);
    }

    @Override // com.amazon.alexa.api.AlexaCardRendererListener
    public void onReceivedRenderCard(String str) {
        if (this.shouldSendAlexaCard) {
            this.eventBus.publish(new Message.Builder().setEventType(CARD_RENDERED_EVENT).build());
        }
    }

    public void release() {
        this.alexaServicesConnection.deregisterListener(this);
        this.eventBus.getSubscriber().unsubscribe(this.shouldSendAlexaCardSubscription);
    }
}
