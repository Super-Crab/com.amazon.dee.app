package com.amazon.alexa.comms.mediaInsights.impl;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.CloseableTraceRecorder;
import com.amazon.alexa.comms.mediaInsights.Trace;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceConnection;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceConnectionListener;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class MessengerTraceRecorderImpl implements CloseableTraceRecorder, TracePublisherServiceConnectionListener {
    @NonNull
    private final EvictingBoundedQueue<Trace> boundedTraceQueue;
    @NonNull
    private TracePublisherServiceConnection tracePublisherServiceConnection;

    public MessengerTraceRecorderImpl(@NonNull TracePublisherServiceConnection tracePublisherServiceConnection, @NonNull EvictingBoundedQueue<Trace> evictingBoundedQueue) {
        if (tracePublisherServiceConnection != null) {
            if (evictingBoundedQueue != null) {
                this.tracePublisherServiceConnection = tracePublisherServiceConnection;
                this.boundedTraceQueue = evictingBoundedQueue;
                tracePublisherServiceConnection.setListener(this);
                return;
            }
            throw new IllegalArgumentException("boundedTraceQueue is null");
        }
        throw new IllegalArgumentException("tracePublisherServiceConnection is null");
    }

    private Message createMessage(@NonNull Trace trace) {
        if (trace != null) {
            Bundle bundle = new Bundle();
            bundle.putString(TracePublisherServiceMessageType.TRACE_MESSAGE_KEY, new GsonBuilder().disableHtmlEscaping().create().toJson(trace));
            Message obtain = Message.obtain(null, TracePublisherServiceMessageType.SEND_TRACE.id(), 1, 0);
            obtain.setData(bundle);
            return obtain;
        }
        throw new IllegalArgumentException("trace is null");
    }

    private void flushTraceMessages() {
        while (true) {
            Trace poll = this.boundedTraceQueue.poll();
            if (poll != null) {
                record(poll, true);
            } else {
                return;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.tracePublisherServiceConnection.close();
    }

    @Override // com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceConnectionListener
    public void onServiceConnected() {
        flushTraceMessages();
    }

    @Override // com.amazon.alexa.comms.mediaInsights.TraceRecorder
    public void record(@NonNull Trace trace) {
        if (trace != null) {
            record(trace, false);
            return;
        }
        throw new IllegalArgumentException("trace is null");
    }

    @Override // com.amazon.alexa.comms.mediaInsights.TraceRecorder
    public void shutdown() {
        close();
    }

    private void record(@NonNull Trace trace, boolean z) {
        if (trace != null) {
            Messenger messenger = this.tracePublisherServiceConnection.getMessenger();
            if (messenger == null && z) {
                Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "reDriven Trace is not being recorded as messenger is null");
                return;
            } else if (messenger == null) {
                this.boundedTraceQueue.add(trace);
                return;
            } else {
                try {
                    messenger.send(createMessage(trace));
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException("trace is null");
    }
}
