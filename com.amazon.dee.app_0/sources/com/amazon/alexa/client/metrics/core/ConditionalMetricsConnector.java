package com.amazon.alexa.client.metrics.core;

import androidx.annotation.NonNull;
/* loaded from: classes6.dex */
public class ConditionalMetricsConnector implements MetricsConnector {
    private MetricsConnector conditionalConnector = null;
    private final GetConnector getConnector;
    private final MetricsStatusProvider metricsStatusProvider;

    @FunctionalInterface
    /* loaded from: classes6.dex */
    public interface GetConnector {
        MetricsConnector get();
    }

    public ConditionalMetricsConnector(@NonNull GetConnector getConnector, @NonNull MetricsStatusProvider metricsStatusProvider) {
        this.getConnector = getConnector;
        this.metricsStatusProvider = metricsStatusProvider;
    }

    private MetricsConnector getConnector() {
        if (this.conditionalConnector == null) {
            if (this.metricsStatusProvider.isEnabled()) {
                this.conditionalConnector = this.getConnector.get();
            } else {
                this.conditionalConnector = new NoOpMetricsConnector();
            }
        }
        return this.conditionalConnector;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void beginSession() {
        getConnector().beginSession();
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void endSession() {
        getConnector().endSession();
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void pauseSession() {
        getConnector().pauseSession();
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void recordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        getConnector().recordEvent(alexaMetricsEvent);
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void resumeSession() {
        getConnector().resumeSession();
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsConnector
    public void shutdown() {
        getConnector().shutdown();
    }
}
