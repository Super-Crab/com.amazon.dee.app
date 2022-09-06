package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.cc;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
class cb extends MessageProcessor<ca> {
    private static final String a = "cb";
    private final AlexaVisualTask b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.cb$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ca.values().length];

        static {
            try {
                a[ca.GET_TASK_COMPONENT_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ca.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(cc.a.RETURN_VALUE_KEY, str);
        }
    }

    private cb(AlexaVisualTask alexaVisualTask) {
        this.b = alexaVisualTask;
    }

    private Bundle a(Bundle bundle) {
        return new a(Bundles.getClient(bundle), this.b.getTaskComponentName()).getBundle();
    }

    public static cb a(AlexaVisualTask alexaVisualTask) {
        return new cb(alexaVisualTask);
    }

    private void b(Bundle bundle) {
        this.b.onStop();
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public ca mo845getMessageType(Message message) {
        try {
            return ca.a(message.what);
        } catch (IllegalArgumentException unused) {
            return ca.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(ca caVar, Bundle bundle, @Nullable Messenger messenger) {
        try {
            int i = AnonymousClass1.a[caVar.ordinal()];
            if (i == 1) {
                reply(messenger, caVar, a(bundle));
            } else if (i != 2) {
                String str = a;
                Log.w(str, "Unsupported message: " + caVar);
            } else {
                b(bundle);
            }
        } catch (RemoteException e) {
            Log.e(a, "Failed to handle incoming message!", e);
        }
    }
}
