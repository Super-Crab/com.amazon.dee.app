package com.amazon.alexa.comms.mediaInsights.common;
/* loaded from: classes6.dex */
public enum TracePublisherServiceMessageType {
    SEND_TRACE(1),
    FLUSH_TRACES(2),
    UNKNOWN_MESSAGE(0);
    
    public static final String TRACE_MESSAGE_KEY = "trace";
    public static final int TRACE_MESSAGE_PLACEHOLDER = 0;
    public static final int TRACE_MESSAGE_VERSION_1 = 1;
    private final int messageIdentifier;

    TracePublisherServiceMessageType(int i) {
        this.messageIdentifier = i;
    }

    public int id() {
        return this.messageIdentifier;
    }

    public static TracePublisherServiceMessageType valueOf(int i) {
        if (i != 1) {
            if (i != 2) {
                return UNKNOWN_MESSAGE;
            }
            return FLUSH_TRACES;
        }
        return SEND_TRACE;
    }
}
