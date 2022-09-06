package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.content.Context;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.transport.HttpTraceTransport;
import com.amazon.alexa.comms.mediaInsights.service.transport.HttpTraceTransportRetryDecorator;
import com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TraceTransportFactory {
    @NonNull
    private Context appContext;
    @NonNull
    private TracePublisherServiceConfiguration configuration;

    public TraceTransportFactory(@NonNull TracePublisherServiceConfiguration tracePublisherServiceConfiguration, @NonNull Context context) {
        if (tracePublisherServiceConfiguration != null) {
            if (context == null) {
                throw new IllegalArgumentException("appContext is null");
            }
            this.configuration = tracePublisherServiceConfiguration;
            this.appContext = context;
            return;
        }
        throw new IllegalArgumentException("configuration is null");
    }

    public TraceTransport create() {
        return HttpTraceTransportRetryDecorator.builder().traceTransport(new HttpTraceTransport(this.appContext, this.configuration, new HttpURLConnectionFactory())).delayInMillis(this.configuration.getMinimumDelayForHttpRetries().intValue()).exponent(this.configuration.getExponentForHttpRetries().intValue()).maxNumberOfRetries(this.configuration.getMaxNumberOfHttpRetries().intValue()).build();
    }
}
