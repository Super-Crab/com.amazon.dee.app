package com.amazon.alexa.client.metrics.kinesis.client;

import com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent;
import com.amazon.alexa.client.metrics.kinesis.event.KinesisInternalEvent;
/* loaded from: classes6.dex */
public interface KinesisInternalEventClient extends KinesisEventClient {
    KinesisEvent createEvent(String str, boolean z);

    KinesisInternalEvent createInternalEvent(String str, Long l, Long l2, Long l3);

    void enableLogDirectedId(boolean z);

    String getDirectedId();

    String getSessionId();

    long getSessionStartTime();

    boolean isDirectedIdEnabled();

    void setDirectedId(String str);

    void setSessionId(String str);

    void setSessionStartTime(long j);
}
