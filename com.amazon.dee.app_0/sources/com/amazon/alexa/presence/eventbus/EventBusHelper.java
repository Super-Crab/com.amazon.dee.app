package com.amazon.alexa.presence.eventbus;

import android.util.Log;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.utils.EventBusUtil;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class EventBusHelper {
    private static final String TAG = Presence.tag();
    private final BatteryOptimizationSubscriber batteryOptimizationSubscriber;
    private final EventBus eventBus;
    private final PresenceSubscriber presenceSubscriber;
    private final PushNotificationSubscriber pushNotificationSubscriber;

    @Inject
    public EventBusHelper(EventBus eventBus, PresenceSubscriber presenceSubscriber, BatteryOptimizationSubscriber batteryOptimizationSubscriber, PushNotificationSubscriber pushNotificationSubscriber) {
        this.eventBus = eventBus;
        this.presenceSubscriber = presenceSubscriber;
        this.batteryOptimizationSubscriber = batteryOptimizationSubscriber;
        this.pushNotificationSubscriber = pushNotificationSubscriber;
    }

    public void subscribeToEventBus() {
        Log.i(TAG, "Subscribing to event bus changes");
        EventBusUtil.subscribeToEventBus(this.eventBus, this.presenceSubscriber);
        EventBusUtil.subscribeToEventBus(this.eventBus, this.batteryOptimizationSubscriber);
        EventBusUtil.subscribeToEventBus(this.eventBus, this.pushNotificationSubscriber);
    }

    public void unsubscribeFromEventBus() {
        Log.i(TAG, "Unsubscribing from event bus");
        EventBusUtil.unsubscribeFromEventBus(this.eventBus, this.presenceSubscriber);
        EventBusUtil.unsubscribeFromEventBus(this.eventBus, this.batteryOptimizationSubscriber);
        EventBusUtil.unsubscribeFromEventBus(this.eventBus, this.pushNotificationSubscriber);
    }
}
