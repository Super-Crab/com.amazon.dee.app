package com.amazon.alexa.mobilytics.connector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
/* loaded from: classes9.dex */
public interface ConnectorExecutor {

    /* loaded from: classes9.dex */
    public interface Factory {
        ConnectorExecutor create(@NonNull MobilyticsConnector mobilyticsConnector);
    }

    String connectorName();

    void executeOnFinalize();

    void executeOnInitialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration);

    void executeRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent);

    void executeSessionPause(@NonNull MobilyticsSession mobilyticsSession);

    void executeSessionResume(@NonNull MobilyticsSession mobilyticsSession);

    void executeSessionStart(@NonNull MobilyticsSession mobilyticsSession);

    void executeSessionStop(@NonNull MobilyticsSession mobilyticsSession);

    void executeUserChanged(@Nullable MobilyticsUser mobilyticsUser);
}
