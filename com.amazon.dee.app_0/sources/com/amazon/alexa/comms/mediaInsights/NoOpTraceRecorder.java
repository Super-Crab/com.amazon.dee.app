package com.amazon.alexa.comms.mediaInsights;
/* loaded from: classes6.dex */
public class NoOpTraceRecorder implements TraceRecorder {
    public static final NoOpTraceRecorder DEFAULT = new NoOpTraceRecorder();

    @Override // com.amazon.alexa.comms.mediaInsights.TraceRecorder
    public void record(Trace trace) {
        trace.toString();
    }

    @Override // com.amazon.alexa.comms.mediaInsights.TraceRecorder
    public void shutdown() {
    }
}
