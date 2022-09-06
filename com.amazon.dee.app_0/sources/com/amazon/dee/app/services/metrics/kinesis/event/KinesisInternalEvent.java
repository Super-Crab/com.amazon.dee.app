package com.amazon.dee.app.services.metrics.kinesis.event;

import com.amazon.dee.app.util.KinesisContextIdUtil;
/* loaded from: classes12.dex */
public interface KinesisInternalEvent extends KinesisEvent {
    Long getEventTimestamp();

    Long getSessionDuration();

    String getSessionId();

    long getSessionStart();

    Long getSessionStop();

    KinesisContextIdUtil getUniqueId();
}
