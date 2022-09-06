package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
/* loaded from: classes9.dex */
public interface RecordChecker {
    void initialize();

    boolean isBlacklisted(@NonNull MobilyticsEvent mobilyticsEvent);

    boolean shouldBeSent(@NonNull DCMEndpoint dCMEndpoint, @NonNull MobilyticsEvent mobilyticsEvent);

    boolean shouldBeSent(@NonNull KinesisEndpoint kinesisEndpoint, @NonNull MobilyticsEvent mobilyticsEvent);
}
