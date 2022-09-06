package com.amazon.alexa.comms.mediaInsights.service.transport;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.factories.HttpURLConnectionFactory;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class HttpTraceTransport implements TraceTransport {
    private static final String WAKE_LOCK_NAME = "TraceHttpGzipTraceFlusherTransportWake";
    private static final String WIFI_LOCK_NAME = "TraceHttpGzipTraceFlusherTransportWifi";
    private TracePublisherServiceConfiguration configuration;
    private HttpURLConnectionFactory connectionFactory;
    private PowerManager powerManager;
    private WifiManager wifiManager;

    public HttpTraceTransport(@NonNull Context context, @NonNull TracePublisherServiceConfiguration tracePublisherServiceConfiguration, @NonNull HttpURLConnectionFactory httpURLConnectionFactory) {
        if (context != null) {
            if (tracePublisherServiceConfiguration == null) {
                throw new IllegalArgumentException("config is null");
            }
            if (httpURLConnectionFactory != null) {
                this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                this.powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
                this.configuration = tracePublisherServiceConfiguration;
                this.connectionFactory = httpURLConnectionFactory;
                return;
            }
            throw new IllegalArgumentException("httpURLConnectionFactory is null");
        }
        throw new IllegalArgumentException("context is null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ee  */
    @Override // com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.alexa.comms.mediaInsights.service.transport.TransportResult send(@lombok.NonNull com.amazon.alexa.comms.mediaInsights.service.transport.TransportPayload r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.comms.mediaInsights.service.transport.HttpTraceTransport.send(com.amazon.alexa.comms.mediaInsights.service.transport.TransportPayload):com.amazon.alexa.comms.mediaInsights.service.transport.TransportResult");
    }
}
