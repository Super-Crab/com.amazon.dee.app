package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.LoggingUtils;
import com.amazon.alexa.comms.mediaInsights.service.configuration.CustomTracePublisherServiceConfig;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfigFactory;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
/* loaded from: classes6.dex */
public class TracePublisherServiceConfigProvider {
    private static final String INTEG_ENV = "integ";

    public static TracePublisherServiceConfiguration provideConfig() {
        TracePublisherServiceConfiguration configuration = CustomTracePublisherServiceConfig.getConfiguration();
        if (configuration != null) {
            Log.i(LoggingUtils.MEDIA_INSIGHTS_TAG, "Custom config being used");
            return configuration;
        }
        TracePublisherServiceConfiguration createReleaseConfig = TracePublisherServiceConfigFactory.createReleaseConfig();
        Log.i(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format("release configs loaded. AMZ_STAGE %s", "release"));
        return createReleaseConfig;
    }
}
