package com.amazon.dee.app.services.metrics.kinesis.client;

import com.amazon.dee.app.services.metrics.kinesis.event.KinesisEvent;
import com.amazon.dee.app.services.metrics.kinesis.event.KinesisInternalEvent;
/* loaded from: classes12.dex */
public interface KinesisInternalEventClient extends KinesisEventClient {
    KinesisEvent createEvent(String str, boolean z);

    KinesisInternalEvent createInternalEvent(String str, long j, Long l, Long l2);

    String getDirectedId();

    void setDirectedId(String str);
}
