package com.amazon.alexa.comms.mediaInsights.service;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetailsFactory;
import com.amazon.alexa.comms.mediaInsights.service.factories.TraceTransportFactory;
import com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport;
import com.amazon.alexa.comms.mediaInsights.service.transport.TransportPayload;
import com.amazon.alexa.comms.mediaInsights.service.transport.TransportResult;
import com.amazon.alexa.comms.mediaInsights.service.utils.StreamUtils;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TraceFlusher {
    private static final String PAYLOAD_ENCODING = "gzip";
    private static final String PAYLOAD_TYPE = "application/json";
    @NonNull
    private DeviceDetailsFactory deviceDetailsFactory;
    @NonNull
    private ExecutorService executorService;
    @NonNull
    private TraceTransportFactory traceTransportFactory;

    public TraceFlusher(@NonNull DeviceDetailsFactory deviceDetailsFactory, @NonNull TraceTransportFactory traceTransportFactory, @NonNull ExecutorService executorService) {
        if (deviceDetailsFactory != null) {
            if (traceTransportFactory == null) {
                throw new IllegalArgumentException("traceTransportFactory is null");
            }
            if (executorService == null) {
                throw new IllegalArgumentException("executorService is null");
            }
            this.deviceDetailsFactory = deviceDetailsFactory;
            this.traceTransportFactory = traceTransportFactory;
            this.executorService = executorService;
            return;
        }
        throw new IllegalArgumentException("deviceDetailsFactory is null");
    }

    private Runnable createRunnableForTransport(final Collection<String> collection) {
        final TraceTransport create = this.traceTransportFactory.create();
        return new Runnable() { // from class: com.amazon.alexa.comms.mediaInsights.service.TraceFlusher.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TransportPayload build = TransportPayload.builder().bytes(StreamUtils.gzipCompress(new TraceRequestModel(TraceFlusher.this.deviceDetailsFactory.create()).toJson(collection))).encoding(TraceFlusher.PAYLOAD_ENCODING).type("application/json").build();
                    long currentTimeMillis = System.currentTimeMillis();
                    TransportResult send = create.send(build);
                    String.format("transport time : %s ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    send.toString();
                } catch (Throwable th) {
                    Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format("transmission of transport message failed: %s %s", th.getCause(), th.getMessage()), th);
                }
            }
        };
    }

    public void asyncFlushTraces(Collection<String> collection) {
        try {
            this.executorService.submit(createRunnableForTransport(collection));
        } catch (Throwable th) {
            Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "failed to flush messages", th);
        }
    }
}
