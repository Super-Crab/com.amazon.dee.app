package com.amazon.alexa.comms.mediaInsights;

import android.content.Context;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceConnection;
import com.amazon.alexa.comms.mediaInsights.common.impl.InAppImplicitBindingIntentProvider;
import com.amazon.alexa.comms.mediaInsights.impl.EvictingBoundedQueue;
import com.amazon.alexa.comms.mediaInsights.impl.MessengerTraceRecorderImpl;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TraceRecorderFactory {
    public static TraceRecorder createNoOpTraceRecorder() {
        return NoOpTraceRecorder.DEFAULT;
    }

    public static CloseableTraceRecorder createTraceRecorder(@NonNull Context context, int i, @NonNull String str) {
        if (context != null) {
            if (str != null) {
                TracePublisherServiceConnection tracePublisherServiceConnection = new TracePublisherServiceConnection(context, new InAppImplicitBindingIntentProvider(), str);
                String.format("TraceRecorderFactory: TracePublisherService bound Status %s", Boolean.valueOf(tracePublisherServiceConnection.connect()));
                return new MessengerTraceRecorderImpl(tracePublisherServiceConnection, new EvictingBoundedQueue(i));
            }
            throw new IllegalArgumentException("uniqueIdentifier is null");
        }
        throw new IllegalArgumentException("context is null");
    }
}
