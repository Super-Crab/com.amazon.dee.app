package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class l extends MessageProcessor<AlexaAttentionSystemSettingsMessageType> {
    private static final String a = AlexaReadinessMessageProcessor.class.getSimpleName();
    private final AlexaAttentionSystemSettingsListener b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.l$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[AlexaAttentionSystemSettingsMessageType.values().length];

        static {
            try {
                a[AlexaAttentionSystemSettingsMessageType.ON_WAKE_SOUND_ENABLED_CHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[AlexaAttentionSystemSettingsMessageType.ON_ENDPOINT_SOUND_ENABLED_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public l(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        this.b = alexaAttentionSystemSettingsListener;
    }

    public static l a(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        return new l(alexaAttentionSystemSettingsListener);
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public AlexaAttentionSystemSettingsMessageType mo845getMessageType(Message message) {
        try {
            return AlexaAttentionSystemSettingsMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(a, "Unrecognized message type", e);
            return AlexaAttentionSystemSettingsMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(AlexaAttentionSystemSettingsMessageType alexaAttentionSystemSettingsMessageType, Bundle bundle, @Nullable Messenger messenger) {
        int i = AnonymousClass1.a[alexaAttentionSystemSettingsMessageType.ordinal()];
        if (i == 1) {
            this.b.onWakeSoundEnabledChanged(Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_WAKE_SOUND_ENABLED));
        } else if (i == 2) {
            this.b.onEndpointSoundEnabledChanged(Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_ENDPOINT_SOUND_ENABLED));
        } else {
            String str = a;
            Log.w(str, "Unsupported message " + alexaAttentionSystemSettingsMessageType);
        }
    }
}
