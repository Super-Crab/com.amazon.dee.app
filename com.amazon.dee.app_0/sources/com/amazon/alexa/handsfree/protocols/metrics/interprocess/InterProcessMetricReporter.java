package com.amazon.alexa.handsfree.protocols.metrics.interprocess;
/* loaded from: classes8.dex */
public abstract class InterProcessMetricReporter {
    public static final String PAYLOAD_IDENTIFIER = "interProcessMetricPayload";

    public abstract void sendMetric(InterProcessMetric interProcessMetric);
}
