package com.amazon.alexa.api;

import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaApiCallbacksWrapper extends AlexaApiCallbacks {
    private final String apiName;
    private final AlexaApiCallbacks callbacks;
    private final AlexaConnectionProxyMapping connectionProxyMapping;
    private final MetricBroadcastSender metricBroadcastSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaApiCallbacksWrapper(AlexaApiCallbacks alexaApiCallbacks, String str, MetricBroadcastSender metricBroadcastSender, AlexaConnectionProxyMapping alexaConnectionProxyMapping) {
        this.callbacks = alexaApiCallbacks;
        this.apiName = str;
        this.metricBroadcastSender = metricBroadcastSender;
        this.connectionProxyMapping = alexaConnectionProxyMapping;
        metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.ATTEMPT.injectWith(str), "", null, alexaApiCallbacks.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    public String getId() {
        return this.callbacks.getId();
    }

    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    @Nullable
    public TimeUnit getTimeoutUnit() {
        return this.callbacks.getTimeoutUnit();
    }

    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    @Nullable
    public Long getTimeoutValue() {
        return this.callbacks.getTimeoutValue();
    }

    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    public void onFailure(ApiCallFailure apiCallFailure) {
        this.callbacks.onFailure(apiCallFailure);
        this.connectionProxyMapping.removeAlexaApiCallbacks(this);
        this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.FAILURE.injectWith(this.apiName).appendToAlexaMetricsName(apiCallFailure.getFailureType().name()), "", null, this.callbacks.getId());
    }

    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    public void onSuccess() {
        this.callbacks.onSuccess();
        this.connectionProxyMapping.removeAlexaApiCallbacks(this);
        this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.SUCCESS.injectWith(this.apiName), "", null, this.callbacks.getId());
    }
}
