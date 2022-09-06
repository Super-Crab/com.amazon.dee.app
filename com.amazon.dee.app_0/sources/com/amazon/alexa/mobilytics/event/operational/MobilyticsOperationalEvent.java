package com.amazon.alexa.mobilytics.event.operational;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import java.util.Map;
/* loaded from: classes9.dex */
public interface MobilyticsOperationalEvent extends MobilyticsEvent {
    @Nullable
    Map<String, String> getDebugInfo();

    @NonNull
    Long getEventNumericValue();

    String getOperationalEventType();
}
