package com.amazon.alexa.comms.mediaInsights.service.configuration;
/* loaded from: classes6.dex */
public class TracePublisherServiceConfigFactory {
    private static final String INTEG_TRACES_HTTP_API = "https://integ.insights.comms.alexa.a2z.com/traces";

    public static TracePublisherServiceConfiguration createIntegConfig() {
        return TracePublisherServiceConfigImpl.builder().traceServiceHttpsEndpoint(INTEG_TRACES_HTTP_API).build();
    }

    public static TracePublisherServiceConfiguration createReleaseConfig() {
        return TracePublisherServiceConfigImpl.builder().build();
    }
}
