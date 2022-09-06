package com.amazon.dee.app.services.messaging;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class MessagingSettingsMetricsHandler {
    private Lazy<Mobilytics> mobilytics;

    public MessagingSettingsMetricsHandler(Lazy<Mobilytics> lazy) {
        this.mobilytics = lazy;
    }

    public void recordAndroidOSNotificationEnabledMetrics(boolean z) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.mo358get().createUserInteractionEvent(z ? AlexaMetricsConstants.MetricEvents.NOTIFICATIONS_OS_SETTING_ENABLED : AlexaMetricsConstants.MetricEvents.NOTIFICATIONS_OS_SETTING_DISABLED, "click", "PushNotifications", "PushNotifications", "1235005e-4e8f-4ef2-82bc-34157415015b");
        createUserInteractionEvent.setChannelName("coreapp");
        this.mobilytics.mo358get().recordUserInteractionEvent(createUserInteractionEvent);
    }
}
