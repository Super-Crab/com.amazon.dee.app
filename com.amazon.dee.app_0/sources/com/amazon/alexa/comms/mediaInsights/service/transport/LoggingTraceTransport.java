package com.amazon.alexa.comms.mediaInsights.service.transport;

import com.amazon.alexa.comms.mediaInsights.service.utils.StreamUtils;
import java.io.IOException;
/* loaded from: classes6.dex */
public class LoggingTraceTransport implements TraceTransport {
    @Override // com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport
    public TransportResult send(TransportPayload transportPayload) throws IOException {
        String.format("LoggingTraceTransport Trace: %n %s", StreamUtils.gzipDecompress(transportPayload.getBytes()));
        return TransportResult.builder().responseCode(200).build();
    }
}
