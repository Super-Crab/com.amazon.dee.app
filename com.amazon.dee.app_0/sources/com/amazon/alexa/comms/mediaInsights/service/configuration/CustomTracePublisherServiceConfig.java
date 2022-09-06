package com.amazon.alexa.comms.mediaInsights.service.configuration;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.LoggingUtils;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class CustomTracePublisherServiceConfig {
    private static volatile TracePublisherServiceConfiguration configuration;

    private CustomTracePublisherServiceConfig() {
    }

    public static TracePublisherServiceConfiguration getConfiguration() {
        return configuration;
    }

    public static synchronized void setConfig(@NonNull TracePublisherServiceConfiguration tracePublisherServiceConfiguration) {
        synchronized (CustomTracePublisherServiceConfig.class) {
            if (tracePublisherServiceConfiguration != null) {
                if (configuration == null) {
                    configuration = tracePublisherServiceConfiguration;
                } else {
                    Log.w(LoggingUtils.MEDIA_INSIGHTS_TAG, "service config is already set");
                }
            } else {
                throw new IllegalArgumentException("inputConfig is null");
            }
        }
    }
}
