package com.amazon.alexa.comms.mediaInsights.service.transport;

import java.io.IOException;
/* loaded from: classes6.dex */
public interface TraceTransport {
    TransportResult send(TransportPayload transportPayload) throws IOException;
}
