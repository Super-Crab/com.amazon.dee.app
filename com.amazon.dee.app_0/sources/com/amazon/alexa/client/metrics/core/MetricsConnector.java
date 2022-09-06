package com.amazon.alexa.client.metrics.core;
/* loaded from: classes6.dex */
public interface MetricsConnector {
    void beginSession();

    void endSession();

    void pauseSession();

    void recordEvent(AlexaMetricsEvent alexaMetricsEvent);

    void resumeSession();

    void shutdown();
}
