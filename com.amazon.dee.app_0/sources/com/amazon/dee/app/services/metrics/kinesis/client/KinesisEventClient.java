package com.amazon.dee.app.services.metrics.kinesis.client;

import com.amazon.dee.app.services.metrics.kinesis.event.KinesisEvent;
/* loaded from: classes12.dex */
public interface KinesisEventClient extends KinesisEventRecorder {
    void addGlobalAttribute(String str, String str2);

    void addGlobalAttribute(String str, String str2, String str3);

    void addGlobalMetric(String str, Double d);

    void addGlobalMetric(String str, String str2, Double d);

    KinesisEvent createEvent(String str);

    void recordEvent(KinesisEvent kinesisEvent);

    void removeGlobalAttribute(String str);

    void removeGlobalAttribute(String str, String str2);

    void removeGlobalMetric(String str);

    void removeGlobalMetric(String str, String str2);

    void updateAppBackgroundStatus(boolean z);
}
