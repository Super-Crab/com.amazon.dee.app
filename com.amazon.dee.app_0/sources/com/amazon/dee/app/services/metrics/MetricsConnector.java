package com.amazon.dee.app.services.metrics;

import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.dee.app.metrics.AlexaMetricsEvent;
/* loaded from: classes12.dex */
public interface MetricsConnector {
    void beginSession();

    void endSession();

    void pauseSession();

    void recordEvent(AlexaMetricsEvent alexaMetricsEvent);

    void recordOperationalEvent(MobilyticsOperationalEvent mobilyticsOperationalEvent);

    void recordUserInteractionEvent(MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent);

    void resumeSession();

    void shutdown();

    void userChanged(UserIdentity userIdentity);
}
