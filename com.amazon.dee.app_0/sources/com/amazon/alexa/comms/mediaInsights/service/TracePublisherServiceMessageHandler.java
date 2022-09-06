package com.amazon.alexa.comms.mediaInsights.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TracePublisherServiceMessageHandler extends Handler {
    @NonNull
    private TracePublisherServiceLogic tracePublisherServiceLogic;

    /* renamed from: com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceMessageHandler$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$comms$mediaInsights$common$TracePublisherServiceMessageType = new int[TracePublisherServiceMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$comms$mediaInsights$common$TracePublisherServiceMessageType[TracePublisherServiceMessageType.SEND_TRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public TracePublisherServiceMessageHandler(@NonNull TracePublisherServiceLogic tracePublisherServiceLogic) {
        if (tracePublisherServiceLogic != null) {
            this.tracePublisherServiceLogic = tracePublisherServiceLogic;
            return;
        }
        throw new IllegalArgumentException("tracePublisherServiceLogic is null");
    }

    private void addTrace(Message message) {
        String string = message.getData().getString(TracePublisherServiceMessageType.TRACE_MESSAGE_KEY);
        if (string != null) {
            this.tracePublisherServiceLogic.addTrace(string);
        } else {
            Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "TracePublisherServiceMessageHandler null trace received in message");
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            if (TracePublisherServiceMessageType.valueOf(message.what).ordinal() != 0) {
                String.format("TracePublisherServiceMessageHandler unknown msg received %s", Integer.valueOf(message.what));
                super.handleMessage(message);
            } else {
                addTrace(message);
            }
        } catch (Throwable th) {
            Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format("TracePublisherServiceMessageHandler failure handling message %s", Integer.valueOf(message.what)), th);
        }
    }
}
