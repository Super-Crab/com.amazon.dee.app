package com.amazon.alexa.mode.drive;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.drive.Card;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.gson.GsonBuilder;
/* loaded from: classes9.dex */
public class HomeChannelInteractor {
    private static final String TAG = "DriveModeHomeIntr";
    private SimpleMultiFilterSubscriber homeChannelStartupEventSubscriber;
    private boolean shouldRepublishDriveModeIngressCard = false;

    private String buildDriveModeIngressCardJson() {
        return new GsonBuilder().create().toJson(new Card(Constants.HOME_CHANNEL_DRIVE_MODE_INGRESS_CARD_ID, Constants.HOME_CHANNEL_CUSTOM_TEMPLATE_TYPE, Constants.HOME_CHANNEL_DRIVE_MODE_INGRESS_CUSTOM_ROUTE, "DriveMode", "DriveMode", "drive_mode_ingress", "drive_mode_ingress", new Card.P13nMetadata(Constants.HOME_CHANNEL_METRIC_SECTION)));
    }

    private void initHomeChannelEventListener() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        this.homeChannelStartupEventSubscriber = new SimpleMultiFilterSubscriber();
        this.homeChannelStartupEventSubscriber.subscribe(new EventTypeMessageFilter(Constants.HOME_CHANNEL_INITIALIZED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.drive.-$$Lambda$HomeChannelInteractor$LvaV2zw-iWenw2EjkBGwmce3_90
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                HomeChannelInteractor.this.onHomeChannelInitialized(message);
            }
        });
        eventBus.subscribe(this.homeChannelStartupEventSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHomeChannelInitialized(Message message) {
        if (this.shouldRepublishDriveModeIngressCard) {
            publishDriveModeIngressCard();
        }
    }

    private void unInitHomeChannelEventListener() {
        if (this.homeChannelStartupEventSubscriber != null) {
            EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
            Preconditions.checkNotNull(eventBus);
            eventBus.unsubscribe(this.homeChannelStartupEventSubscriber);
        }
    }

    public void initialize() {
        initHomeChannelEventListener();
    }

    public void publishDriveModeIngressCard() {
        removeDriveModeIngressCard();
        this.shouldRepublishDriveModeIngressCard = true;
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        String buildDriveModeIngressCardJson = buildDriveModeIngressCardJson();
        String str = "Adding drivemode ingress card: " + buildDriveModeIngressCardJson;
        eventBus.publish(new Message.Builder().setEventType(Constants.HOME_CHANNEL_EVENT_ADD_CARD).setPayload(buildDriveModeIngressCardJson).build());
    }

    public void removeDriveModeIngressCard() {
        this.shouldRepublishDriveModeIngressCard = false;
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        String buildDriveModeIngressCardJson = buildDriveModeIngressCardJson();
        String str = "Removing drivemode ingress card: " + buildDriveModeIngressCardJson;
        eventBus.publish(new Message.Builder().setEventType(Constants.HOME_CHANNEL_EVENT_REMOVE_CARD).setPayload(buildDriveModeIngressCardJson).build());
    }

    public void unInitialize() {
        unInitHomeChannelEventListener();
    }
}
