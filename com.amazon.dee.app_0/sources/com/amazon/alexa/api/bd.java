package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
class bd extends MessageProcessor<be> {
    private static final String a = "bd";
    private final AlexaMetricsListener b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.bd$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[be.values().length];

        static {
            try {
                a[be.ON_METRICS_REPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private bd(AlexaMetricsListener alexaMetricsListener) {
        this.b = alexaMetricsListener;
    }

    public static bd a(AlexaMetricsListener alexaMetricsListener) {
        return new bd(alexaMetricsListener);
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public be mo845getMessageType(Message message) {
        try {
            return be.a(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(a, "Unrecognized message type", e);
            return be.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(be beVar, Bundle bundle, @Nullable Messenger messenger) {
        if (AnonymousClass1.a[beVar.ordinal()] == 1) {
            this.b.onMetricsReport(AlexaMetricsData.fromBundle(bundle));
            return;
        }
        String str = a;
        Log.w(str, "Unsupported message " + beVar);
    }
}
