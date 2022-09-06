package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
class bo extends MessageProcessor<bm> {
    private static final String a = "bo";
    private final ResultCallbacks b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.bo$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[bm.values().length];

        static {
            try {
                a[bm.ON_SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[bm.ON_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private bo(ResultCallbacks resultCallbacks) {
        this.b = resultCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static bo a(ResultCallbacks resultCallbacks) {
        Preconditions.notNull(resultCallbacks, "null implementation!");
        return new bo(resultCallbacks);
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public bm mo845getMessageType(Message message) {
        try {
            return bm.a(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(a, "Unrecognized message type", e);
            return bm.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(bm bmVar, Bundle bundle, @Nullable Messenger messenger) {
        int i = AnonymousClass1.a[bmVar.ordinal()];
        if (i == 1) {
            this.b.onSuccess();
        } else if (i == 2) {
            this.b.onFailure(Bundles.getString(bundle, bn.FAILURE_MESSAGE));
        } else {
            String str = a;
            Log.w(str, "Unsupported message " + bmVar);
        }
    }
}
