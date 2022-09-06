package com.amazon.alexa.comms.mediaInsights.service.factories;

import com.amazon.alexa.comms.mediaInsights.service.TraceFlusher;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetailsFactory;
import java.util.concurrent.ExecutorService;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TraceFlusherFactory {
    @NonNull
    private DeviceDetailsFactory deviceDetailsFactory;
    @NonNull
    private ExecutorService executorService;
    @NonNull
    private TraceTransportFactory flusherTransportFactory;

    public TraceFlusherFactory(@NonNull TraceTransportFactory traceTransportFactory, @NonNull DeviceDetailsFactory deviceDetailsFactory, @NonNull ExecutorService executorService) {
        if (traceTransportFactory != null) {
            if (deviceDetailsFactory == null) {
                throw new IllegalArgumentException("deviceDetailsFactory is null");
            }
            if (executorService == null) {
                throw new IllegalArgumentException("executorService is null");
            }
            this.flusherTransportFactory = traceTransportFactory;
            this.deviceDetailsFactory = deviceDetailsFactory;
            this.executorService = executorService;
            return;
        }
        throw new IllegalArgumentException("flusherTransportFactory is null");
    }

    public TraceFlusher create() {
        return new TraceFlusher(this.deviceDetailsFactory, this.flusherTransportFactory, this.executorService);
    }
}
