package com.amazon.alexa.client.metrics.kinesis.event;

import com.amazon.alexa.client.metrics.kinesis.util.KinesisContextIdUtil;
/* loaded from: classes6.dex */
public interface KinesisInternalEvent extends KinesisEvent {
    Long getEventTimestamp();

    Long getSessionDuration();

    String getSessionId();

    long getSessionStart();

    Long getSessionStop();

    KinesisContextIdUtil getUniqueId();
}
