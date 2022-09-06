package com.amazon.alexa.client.metrics.kinesis.client;

import com.amazon.alexa.client.metrics.kinesis.event.KinesisEvent;
/* loaded from: classes6.dex */
public interface KinesisEventClient {
    void addGlobalAttribute(String str, String str2);

    void addGlobalAttribute(String str, String str2, String str3);

    void addGlobalMetric(String str, Double d);

    void addGlobalMetric(String str, String str2, Double d);

    KinesisEvent createEvent(String str);

    long getEventsSizePersisted();

    void recordEvent(KinesisEvent kinesisEvent);

    void removeGlobalAttribute(String str);

    void removeGlobalAttribute(String str, String str2);

    void removeGlobalMetric(String str);

    void removeGlobalMetric(String str, String str2);

    void submitEvents();

    void updateAppBackgroundStatus(boolean z);
}
