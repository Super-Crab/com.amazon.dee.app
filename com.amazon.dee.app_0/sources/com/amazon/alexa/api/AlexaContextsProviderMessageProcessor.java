package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Collection;
/* loaded from: classes6.dex */
class AlexaContextsProviderMessageProcessor extends MessageProcessor<AlexaContextsProviderMessageType> {
    private static final String TAG = "AlexaContextsProviderMessageProcessor";
    private final AlexaContextsProvider alexaContextsProvider;

    /* renamed from: com.amazon.alexa.api.AlexaContextsProviderMessageProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaContextsProviderMessageType = new int[AlexaContextsProviderMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaContextsProviderMessageType[AlexaContextsProviderMessageType.GET_ALEXA_CONTEXTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaContextsProviderMessageType[AlexaContextsProviderMessageType.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private AlexaContextsProviderMessageProcessor(AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaContextsProvider, "AlexaContextsProvider can't be null");
        this.alexaContextsProvider = alexaContextsProvider;
    }

    public static AlexaContextsProviderMessageProcessor create(AlexaContextsProvider alexaContextsProvider) {
        return new AlexaContextsProviderMessageProcessor(alexaContextsProvider);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaContextsProviderMessageType mo845getMessageType(Message message) {
        try {
            return AlexaContextsProviderMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return AlexaContextsProviderMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaContextsProviderMessageType alexaContextsProviderMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        try {
            if (alexaContextsProviderMessageType.ordinal() != 1) {
                String str = TAG;
                Log.w(str, "Unsupported message " + alexaContextsProviderMessageType);
            } else {
                Preconditions.notNull(messenger, "replyTo messenger can't be null for GET_ALEXA_CONTEXTS");
                reply(messenger, alexaContextsProviderMessageType, BundleTransformer.getDefaultInstance().toBundle((Collection) this.alexaContextsProvider.getAlexaContexts()));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message!", e);
        }
    }
}
