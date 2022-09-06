package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
@Deprecated
/* loaded from: classes13.dex */
public interface InternalEventClient extends EventClient {
    void addEventObserver(EventObserver eventObserver);

    AnalyticsEvent createEvent(String str, boolean z);

    InternalEvent createInternalEvent(String str, long j, Long l, Long l2);

    String getSessionId();

    long getSessionStartTime();

    void removeEventObserver(EventObserver eventObserver);

    void setSessionId(String str);

    void setSessionStartTime(long j);
}
