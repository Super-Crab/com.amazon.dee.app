package com.amazon.alexa.comms.mediaInsights.service.factories;

import com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceLogic;
import com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceMessageHandler;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TracePublisherServiceMessageHandlerFactory {
    public static TracePublisherServiceMessageHandler create(@NonNull TracePublisherServiceLogic tracePublisherServiceLogic) {
        if (tracePublisherServiceLogic != null) {
            return new TracePublisherServiceMessageHandler(tracePublisherServiceLogic);
        }
        throw new IllegalArgumentException("serviceLogic is null");
    }
}
