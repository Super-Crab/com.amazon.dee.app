package com.amazon.alexa.mobilytics.executor;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.timeline.TimelineMessage;
/* loaded from: classes9.dex */
public interface Executor {
    void executeRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent);

    void executeTimelineEvent(TimelineMessage timelineMessage);
}
