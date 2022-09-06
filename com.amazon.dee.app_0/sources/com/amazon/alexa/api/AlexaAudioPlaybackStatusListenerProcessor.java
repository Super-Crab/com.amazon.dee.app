package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import java.util.HashMap;
/* loaded from: classes6.dex */
public class AlexaAudioPlaybackStatusListenerProcessor extends MessageProcessor<AlexaAudioPlaybackStatusListenerMessageType> {
    private static final String TAG = "AlexaAudioPlaybackStatusListenerProcessor";
    private final AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener;

    /* renamed from: com.amazon.alexa.api.AlexaAudioPlaybackStatusListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaAudioPlaybackStatusListenerMessageType = new int[AlexaAudioPlaybackStatusListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioPlaybackStatusListenerMessageType[AlexaAudioPlaybackStatusListenerMessageType.AUDIO_PLAYBACK_STATUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    AlexaAudioPlaybackStatusListenerProcessor(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        this.alexaAudioPlaybackStatusListener = alexaAudioPlaybackStatusListener;
    }

    public static AlexaAudioPlaybackStatusListenerProcessor create(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        return new AlexaAudioPlaybackStatusListenerProcessor(alexaAudioPlaybackStatusListener);
    }

    private void processAudioPlaybackStatus(Bundle bundle) {
        this.alexaAudioPlaybackStatusListener.onAudioPlaybackStatusChanged((HashMap) Bundles.getOptionalSerializable(bundle, AlexaAudioPlaybackStatusListenerArgumentKey.AUDIO_PLAYBACK_STATUS_KEY, HashMap.class));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaAudioPlaybackStatusListenerMessageType mo845getMessageType(Message message) {
        try {
            return AlexaAudioPlaybackStatusListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return AlexaAudioPlaybackStatusListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaAudioPlaybackStatusListenerMessageType alexaAudioPlaybackStatusListenerMessageType, Bundle bundle, Messenger messenger) {
        if (alexaAudioPlaybackStatusListenerMessageType.ordinal() == 1) {
            processAudioPlaybackStatus(bundle);
            return;
        }
        String str = TAG;
        Log.w(str, "Unsupported message: " + alexaAudioPlaybackStatusListenerMessageType);
    }
}
