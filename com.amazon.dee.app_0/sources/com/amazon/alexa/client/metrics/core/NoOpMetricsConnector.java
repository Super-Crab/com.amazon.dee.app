package com.amazon.alexa.client.metrics.core;
/* loaded from: classes6.dex */
public class NoOpMetricsConnector implements MetricsConnector {
    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void beginSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void endSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void pauseSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void recordEvent(AlexaMetricsEvent alexaMetricsEvent) {
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void resumeSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void shutdown() {
    }
}
