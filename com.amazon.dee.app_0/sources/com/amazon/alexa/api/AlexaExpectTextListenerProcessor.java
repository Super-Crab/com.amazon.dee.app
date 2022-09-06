package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public final class AlexaExpectTextListenerProcessor extends MessageProcessor<ExpectTextMessageType> {
    private final AlexaExpectTextListener alexaExpectTextListener;

    public AlexaExpectTextListenerProcessor(@NonNull AlexaExpectTextListener alexaExpectTextListener) {
        this.alexaExpectTextListener = alexaExpectTextListener;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ExpectTextMessageType mo845getMessageType(Message message) {
        try {
            return ExpectTextMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ExpectTextMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ExpectTextMessageType expectTextMessageType, Bundle bundle, Messenger messenger) {
        if (expectTextMessageType == ExpectTextMessageType.ON_EXPECT_TEXT_RECEIVED) {
            this.alexaExpectTextListener.onExpectTextReceived();
            return;
        }
        String simpleName = AlexaExpectTextListenerProcessor.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + expectTextMessageType);
    }
}
