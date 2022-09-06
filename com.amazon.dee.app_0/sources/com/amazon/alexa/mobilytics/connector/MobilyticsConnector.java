package com.amazon.alexa.mobilytics.connector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
/* loaded from: classes9.dex */
public interface MobilyticsConnector {
    String name();

    void onFinalize();

    void onInitialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration);

    void onRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent);

    void onSessionPause(@NonNull MobilyticsSession mobilyticsSession);

    void onSessionResume(@NonNull MobilyticsSession mobilyticsSession);

    void onSessionStart(@NonNull MobilyticsSession mobilyticsSession);

    void onSessionStop(@NonNull MobilyticsSession mobilyticsSession);

    void onUserChanged(@Nullable MobilyticsUser mobilyticsUser);
}
