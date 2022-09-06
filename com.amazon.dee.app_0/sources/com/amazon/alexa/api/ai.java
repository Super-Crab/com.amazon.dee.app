package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public abstract class ai extends MessageProcessor<ClientConnectionControllerMessageType> {
    private static final String TAG = "ai";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.ai$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ClientConnectionControllerMessageType.values().length];

        static {
            try {
                a[ClientConnectionControllerMessageType.ON_FORCE_DISCONNECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ClientConnectionControllerMessageType.ON_START_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ClientConnectionControllerMessageType mo845getMessageType(Message message) {
        try {
            return ClientConnectionControllerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return ClientConnectionControllerMessageType.UNKNOWN;
        }
    }

    protected abstract void onForceDisconnect();

    protected abstract void onStartService();

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ClientConnectionControllerMessageType clientConnectionControllerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        int i = AnonymousClass1.a[clientConnectionControllerMessageType.ordinal()];
        if (i == 1) {
            onForceDisconnect();
        } else if (i == 2) {
            onStartService();
        } else {
            String str = TAG;
            Log.w(str, "Unsupported message " + clientConnectionControllerMessageType);
        }
    }
}
