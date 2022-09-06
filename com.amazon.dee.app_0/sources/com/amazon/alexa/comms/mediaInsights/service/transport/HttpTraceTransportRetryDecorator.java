package com.amazon.alexa.comms.mediaInsights.service.transport;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.LoggingUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.HttpRetryException;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class HttpTraceTransportRetryDecorator implements TraceTransport {
    private final int delayInMillis;
    private final int exponent;
    private final int maxNumberOfRetries;
    @NonNull
    private final TraceTransport traceTransport;

    /* loaded from: classes6.dex */
    public static class HttpTraceTransportRetryDecoratorBuilder {
        private int delayInMillis;
        private int exponent;
        private int maxNumberOfRetries;
        private TraceTransport traceTransport;

        HttpTraceTransportRetryDecoratorBuilder() {
        }

        public HttpTraceTransportRetryDecorator build() {
            return new HttpTraceTransportRetryDecorator(this.traceTransport, this.maxNumberOfRetries, this.exponent, this.delayInMillis);
        }

        public HttpTraceTransportRetryDecoratorBuilder delayInMillis(int i) {
            this.delayInMillis = i;
            return this;
        }

        public HttpTraceTransportRetryDecoratorBuilder exponent(int i) {
            this.exponent = i;
            return this;
        }

        public HttpTraceTransportRetryDecoratorBuilder maxNumberOfRetries(int i) {
            this.maxNumberOfRetries = i;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpTraceTransportRetryDecorator.HttpTraceTransportRetryDecoratorBuilder(traceTransport=");
            outline107.append(this.traceTransport);
            outline107.append(", maxNumberOfRetries=");
            outline107.append(this.maxNumberOfRetries);
            outline107.append(", exponent=");
            outline107.append(this.exponent);
            outline107.append(", delayInMillis=");
            return GeneratedOutlineSupport1.outline86(outline107, this.delayInMillis, ")");
        }

        public HttpTraceTransportRetryDecoratorBuilder traceTransport(TraceTransport traceTransport) {
            this.traceTransport = traceTransport;
            return this;
        }
    }

    HttpTraceTransportRetryDecorator(@NonNull TraceTransport traceTransport, int i, int i2, int i3) {
        if (traceTransport != null) {
            this.traceTransport = traceTransport;
            this.maxNumberOfRetries = i;
            this.exponent = i2;
            this.delayInMillis = i3;
            return;
        }
        throw new IllegalArgumentException("traceTransport is null");
    }

    public static HttpTraceTransportRetryDecoratorBuilder builder() {
        return new HttpTraceTransportRetryDecoratorBuilder();
    }

    private TransportResult retry(TransportPayload transportPayload, HttpRetryException httpRetryException) throws IOException {
        long j = this.delayInMillis;
        HttpRetryException e = httpRetryException;
        for (int i = 1; i <= this.maxNumberOfRetries; i++) {
            Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format("retry http request: retry no %s, retry reason: %s %s", Integer.valueOf(i), e.getReason(), Integer.valueOf(e.responseCode())));
            sleep(j);
            try {
                return this.traceTransport.send(transportPayload);
            } catch (HttpRetryException e2) {
                e = e2;
                j *= this.exponent;
            }
        }
        throw e;
    }

    private void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport
    public TransportResult send(TransportPayload transportPayload) throws IOException {
        try {
            return this.traceTransport.send(transportPayload);
        } catch (HttpRetryException e) {
            return retry(transportPayload, e);
        }
    }
}
