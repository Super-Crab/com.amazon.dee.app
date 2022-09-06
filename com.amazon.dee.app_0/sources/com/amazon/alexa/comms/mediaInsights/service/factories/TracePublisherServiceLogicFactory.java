package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.content.Context;
import com.amazon.alexa.comms.mediaInsights.service.TraceFlusher;
import com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceLogic;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetailsFactory;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.NetworkDetailsFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TracePublisherServiceLogicFactory {
    private static TracePublisherServiceLogic tracePublisherServiceLogic;

    private static DeviceDetailsFactory createDeviceDetailsFactory(Context context) {
        return new DeviceDetailsFactory(createNetworkDetailsFactory(context));
    }

    private static ExecutorService createExecutorService(TracePublisherServiceConfiguration tracePublisherServiceConfiguration) {
        return Executors.newFixedThreadPool(tracePublisherServiceConfiguration.getFlushThreadPoolSize().intValue());
    }

    private static NetworkDetailsFactory createNetworkDetailsFactory(Context context) {
        return new NetworkDetailsFactory(context);
    }

    private static TraceFlusher createTraceFlusher(TracePublisherServiceConfiguration tracePublisherServiceConfiguration, Context context) {
        return new TraceFlusherFactory(createTraceFlusherTransportFactory(tracePublisherServiceConfiguration, context), createDeviceDetailsFactory(context), createExecutorService(tracePublisherServiceConfiguration)).create();
    }

    private static TraceTransportFactory createTraceFlusherTransportFactory(TracePublisherServiceConfiguration tracePublisherServiceConfiguration, Context context) {
        return new TraceTransportFactory(tracePublisherServiceConfiguration, context);
    }

    public static synchronized TracePublisherServiceLogic getInstance(@NonNull TracePublisherServiceConfiguration tracePublisherServiceConfiguration, @NonNull Context context) {
        TracePublisherServiceLogic tracePublisherServiceLogic2;
        synchronized (TracePublisherServiceLogicFactory.class) {
            if (tracePublisherServiceConfiguration == null) {
                throw new IllegalArgumentException("config is null");
            }
            if (context != null) {
                if (tracePublisherServiceLogic == null) {
                    tracePublisherServiceLogic = new TracePublisherServiceLogic(tracePublisherServiceConfiguration, createTraceFlusher(tracePublisherServiceConfiguration, context), context);
                }
                tracePublisherServiceLogic2 = tracePublisherServiceLogic;
            } else {
                throw new IllegalArgumentException("appContext is null");
            }
        }
        return tracePublisherServiceLogic2;
    }
}
