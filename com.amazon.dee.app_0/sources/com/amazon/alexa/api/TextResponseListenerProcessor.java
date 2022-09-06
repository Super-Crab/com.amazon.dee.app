package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.TextResponseListenerArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
final class TextResponseListenerProcessor extends MessageProcessor<TextResponseListenerMessageType> {
    private final AlexaTextResponseListener alexaTextResponseListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.TextResponseListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[TextResponseListenerMessageType.values().length];

        static {
            try {
                a[TextResponseListenerMessageType.ON_TEXT_RECEIVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextResponseListenerProcessor(AlexaTextResponseListener alexaTextResponseListener) {
        this.alexaTextResponseListener = alexaTextResponseListener;
    }

    public static TextResponseListenerProcessor create(AlexaTextResponseListener alexaTextResponseListener) {
        return new TextResponseListenerProcessor(alexaTextResponseListener);
    }

    private void processOnTextResponse(Bundle bundle) {
        this.alexaTextResponseListener.onTextReceived((TextResponse) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, TextResponseListenerArgumentType.a.RESPONSE), TextResponse.class));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public TextResponseListenerMessageType mo845getMessageType(Message message) {
        try {
            return TextResponseListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return TextResponseListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(TextResponseListenerMessageType textResponseListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (AnonymousClass1.a[textResponseListenerMessageType.ordinal()] == 1) {
            processOnTextResponse(bundle);
            return;
        }
        String simpleName = TextResponseListenerProcessor.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + textResponseListenerMessageType);
    }
}
