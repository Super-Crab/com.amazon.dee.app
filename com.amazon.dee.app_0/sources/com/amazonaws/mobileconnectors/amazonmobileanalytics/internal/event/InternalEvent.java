package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.Id;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable;
@Deprecated
/* loaded from: classes13.dex */
public interface InternalEvent extends AnalyticsEvent, JSONSerializable {
    ClientContext createClientContext(String str);

    Long getEventTimestamp();

    String getSdkName();

    String getSdkVersion();

    Long getSessionDuration();

    String getSessionId();

    long getSessionStart();

    Long getSessionStop();

    Id getUniqueId();
}
