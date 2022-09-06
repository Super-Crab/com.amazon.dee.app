package com.amazon.alexa.mobilytics.event;

import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import java.util.Map;
/* loaded from: classes9.dex */
public interface MobilyticsEventFactory {
    @Nullable
    MobilyticsOperationalEvent getOperationalEvent(@Nullable String str, @Nullable String str2, @Nullable Map<String, Object> map);

    @Nullable
    MobilyticsUserInteractionEvent getUserInteractionEvent(@Nullable String str, @Nullable String str2, @Nullable Map<String, Object> map);
}
