package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
abstract class ForceDisconnectMessageProcessor extends MessageProcessor<as> {
    private static final String TAG = "ForceDisconnectMessageProcessor";

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public as mo845getMessageType(Message message) {
        if (as.ON_FORCE_DISCONNECT.ordinal() == message.what) {
            return as.ON_FORCE_DISCONNECT;
        }
        Log.e(TAG, "Unrecognized message type");
        return as.UNKNOWN;
    }

    protected abstract void onForceDisconnect();

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(as asVar, Bundle bundle, @Nullable Messenger messenger) {
        if (as.ON_FORCE_DISCONNECT.equals(asVar)) {
            onForceDisconnect();
            return;
        }
        String str = TAG;
        Log.w(str, "Unsupported message " + asVar);
    }
}
