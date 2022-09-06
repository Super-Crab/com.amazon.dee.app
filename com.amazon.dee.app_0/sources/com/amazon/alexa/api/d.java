package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
class d extends MessageProcessor<e> {
    private static final String a = "d";
    private final AlertsListener b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.d$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[e.values().length];

        static {
            try {
                a[e.ON_ALERT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.ON_ALERT_FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private d(AlertsListener alertsListener) {
        this.b = alertsListener;
    }

    public static d a(AlertsListener alertsListener) {
        Preconditions.notNull(alertsListener, "null implementation!");
        return new d(alertsListener);
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public e mo845getMessageType(Message message) {
        try {
            return e.a(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(a, "Unrecognized message type", e);
            return e.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(e eVar, Bundle bundle, @Nullable Messenger messenger) {
        String string = Bundles.getString(bundle, b.ALERT_ID);
        AlertType fromOrdinal = AlertType.fromOrdinal(Bundles.getInteger(bundle, b.ALERT_TYPE));
        int i = AnonymousClass1.a[eVar.ordinal()];
        if (i == 1) {
            this.b.onAlertStarted(string, fromOrdinal);
        } else if (i == 2) {
            this.b.onAlertFinished(string, fromOrdinal);
        } else {
            String str = a;
            Log.w(str, "Unsupported message " + eVar);
        }
    }
}
